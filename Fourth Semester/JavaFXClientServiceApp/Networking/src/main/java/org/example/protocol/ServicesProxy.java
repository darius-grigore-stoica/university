package org.example.protocol;

import org.example.*;
import org.example.dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionException;
import java.util.concurrent.LinkedBlockingDeque;

public class ServicesProxy implements IServices {

    private String host;
    private int port;

    private IObserver client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> responses;
    private volatile boolean finished;

    public ServicesProxy(String host, int port) {
        this.host = host;
        this.port = port;
        responses = new LinkedBlockingDeque<>();
    }

    @Override
    public void login(User u, IObserver client) throws ServiceException {
        initializeConnection();

        UserDTO udto = DTOUtils.getUserDTO(u);
        Request req = new Request.Builder().type(RequestType.LOGIN).data(udto).build();

        System.out.println("Sending request: " + req.type() + " " + req.data());
        sendRequest(req);

        Response response = readResponse();
        System.out.println(response);
        if (response.type() == ResponseType.OK) {
            this.client = client;
        }
        if (response.type() == ResponseType.ERROR) {
            closeConnection();
            throw new ServiceException("Authentication failed");
        }
    }

    @Override
    public void logout(User u, IObserver client) throws ServiceException {
        UserDTO udto = DTOUtils.getUserDTO(u);
        Request req = new Request.Builder().type(RequestType.LOGOUT).data(udto).build();
        sendRequest(req);

        Response response = readResponse();
//        closeConnection();
        if (response.type() == ResponseType.ERROR)
            throw new ServiceException("ERROR " + response.data().toString());
    }

    @Override
    public void enroll(Child child, Competition competition, IObserver client) throws ServiceException {

        ChildDTO cdto = DTOUtils.getChildDTO(child);
        CompetitionDTO compdto = DTOUtils.getCompetitionDTO(competition);
        Object[] data = new Object[]{cdto, compdto};

        Request req=new Request.Builder().type(RequestType.ENROLL).data(data).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR)
            throw new ServiceException("ERROR " + response.data().toString());
    }

    @Override
    public Child[] search(Competition competition, AgeGroup ageGroup, IObserver client) throws ServiceException {
        CompetitionDTO compdto = DTOUtils.getCompetitionDTO(competition);
        AgeGroupDTO ageGroupDTO = DTOUtils.getAgeGroupDTO(ageGroup);

        Object[] data = new Object[]{compdto, ageGroupDTO};
        Request req=new Request.Builder().type(RequestType.SEARCH).data(data).build();

        sendRequest(req);

        Response response = readResponse();

        if (response.type() == ResponseType.CHILDREN) {
            ChildDTO[] childDTOS = (ChildDTO[]) response.data();
            Child[] children = new Child[childDTOS.length];
            for(int i = 0; i < childDTOS.length; i++){
                children[i] = DTOUtils.getChildFromDTO(childDTOS[i]);
            }
            return children;
        }
        throw new ServiceException("No children found");
    }

    @Override
    public Competition[] getAllCompetitions(User u, IObserver client) throws ServiceException {
        UserDTO udto = DTOUtils.getUserDTO(u);
        Request req = new Request.Builder().type(RequestType.GET_ALL_COMPETITIONS).data(udto).build();
        System.out.println("Request " + req);

        sendRequest(req);
        Response response = readResponse();

        if (response.type() == ResponseType.COMPETITIONS) {
            CompetitionDTO[] competitions = (CompetitionDTO[]) response.data();

            return DTOUtils.getCompetitionFromDTO(competitions);
        }
        throw new ServiceException("No competitions found");
    }

    @Override
    public Child[] getAllChildren(User u, IObserver client) throws ServiceException {
        UserDTO udto = DTOUtils.getUserDTO(u);
        Request req = new Request.Builder().type(RequestType.GET_ALL_CHILDREN).data(udto).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.CHILDREN) {
            ChildDTO[] children = (ChildDTO[]) response.data();
            return DTOUtils.getChildrenFromDTO(children);
        }
        throw new ServiceException("No children found");
    }

    @Override
    public Enrollment[] getAllEnrollments(User u, IObserver client) throws ServiceException {
        UserDTO udto = DTOUtils.getUserDTO(u);
        Request req = new Request.Builder().type(RequestType.GET_ALL_ENROLLMENTS).data(udto).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ENROLLMENTS) {
            EnrollmentDTO[] enrollments = (EnrollmentDTO[]) response.data();
            return DTOUtils.getEnrollmentsFromDTO(enrollments);
        }
        throw new ServiceException("No enrollments found");
    }
    private void sendRequest(Request request) throws ServiceException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new ServiceException("Error sending object " + e);
        }
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = responses.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void initializeConnection() {
        System.out.println("Initializing connection");
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }

    private class ReaderThread implements Runnable {
        @Override
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    if (isUpdate((Response) response)) {
                        handleUpdate((Response) response);
                    } else {
                        try {
                            responses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private void handleUpdate(Response response) {
        Enrollment enrollment = DTOUtils.getEnrollmentFromDTO((EnrollmentDTO) response.data());
        try{
            client.updateEnrollmentsNotify(enrollment);
        } catch (ServiceException ex){
            System.out.println("nu s-a putut actualiza");
        }
    }

    private boolean isUpdate(Response response) {
        return response.type() == ResponseType.ENROLLMENT_ADDED;
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
