package org.example.protocol;

import org.example.*;
import org.example.dto.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientWorker implements Runnable, IObserver {
    private IServices server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private volatile boolean connected;

    public ClientWorker(IServices server, Socket connection) {
        this.server = server;
        this.connection = connection;

        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private Response handleRequest(Request request) {
        System.out.println("Request received: " + request);
        if (request.type() == RequestType.LOGIN) {
            UserDTO userDTO = (UserDTO) request.data();
            User user = DTOUtils.getUserFromDTO(userDTO);
            try {
                server.login(user, this);
                System.out.println("Login succeeded...");
                return okResponse;
            } catch (ServiceException e) {
                System.out.println("Login failed..." + e.getMessage());
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.LOGOUT) {
            UserDTO userDTO = (UserDTO) request.data();
            User user = DTOUtils.getUserFromDTO(userDTO);
            System.out.println("Logout request..." + user);
            try {
                server.logout(user, this);
                connected = false;
                System.out.println("Logout succeeded...");
                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.ENROLL) {
            System.out.println("Enroll request...");

            Object[] data = (Object[]) request.data();
            ChildDTO childDTO = (ChildDTO) data[0];

            CompetitionDTO competitionDTO = (CompetitionDTO) data[1];
            Child child = DTOUtils.getChildFromDTO(childDTO);

            Competition competition = DTOUtils.getCompetitionFromDTO(competitionDTO);
            try {
                server.enroll(child, competition, this);
                System.out.println("Enroll succeeded...");

                return okResponse;
            } catch (Exception e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.GET_ALL_COMPETITIONS) {
            UserDTO userDTO = (UserDTO) request.data();
            User user = DTOUtils.getUserFromDTO(userDTO);
            try {
                Competition[] competitions = server.getAllCompetitions(user, this);
                CompetitionDTO[] competitionDTOs = new CompetitionDTO[competitions.length];
                for (int i = 0; i < competitions.length; i++) {
                    competitionDTOs[i] = DTOUtils.getCompetitionDTO(competitions[i]);
                }
                return new Response.Builder().type(ResponseType.COMPETITIONS).data(competitionDTOs).build();
            } catch (ServiceException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.GET_ALL_CHILDREN) {
            UserDTO userDTO = (UserDTO) request.data();
            User user = DTOUtils.getUserFromDTO(userDTO);
            try {
                Child[] children = server.getAllChildren(user, this);
                ChildDTO[] childrenDTO = new ChildDTO[children.length];
                for (int i = 0; i < children.length; i++) {
                    childrenDTO[i] = DTOUtils.getChildDTO(children[i]);
                }
                return new Response.Builder().type(ResponseType.CHILDREN).data(childrenDTO).build();
            } catch (ServiceException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type() == RequestType.GET_ALL_ENROLLMENTS) {
            UserDTO userDTO = (UserDTO) request.data();
            User user = DTOUtils.getUserFromDTO(userDTO);
            try {
                Enrollment[] enrollments = server.getAllEnrollments(user, this);
                EnrollmentDTO[] enrollmentDTOs = new EnrollmentDTO[enrollments.length];
                for (int i = 0; i < enrollments.length; i++) {
                    enrollmentDTOs[i] = DTOUtils.getEnrollmentDTO(enrollments[i]);
                }
                System.out.println("Enrollments: " + enrollmentDTOs.length);
                return new Response.Builder().type(ResponseType.ENROLLMENTS).data(enrollmentDTOs).build();
            } catch (ServiceException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type() == RequestType.SEARCH) {
            Object[] data = (Object[]) request.data();

            CompetitionDTO competitionDTO = (CompetitionDTO) data[0];
            AgeGroupDTO ageGroupDTO = (AgeGroupDTO) data[1];

            Competition competition = DTOUtils.getCompetitionFromDTO(competitionDTO);
            AgeGroup ageGroup = DTOUtils.getAgeGroupFromDTO(ageGroupDTO);

            try {
                Child[] children = server.search(competition, ageGroup, this);
                ChildDTO[] childrenDTO = new ChildDTO[children.length];
                for (int i = 0; i < children.length; i++) {
                    childrenDTO[i] = DTOUtils.getChildDTO(children[i]);
                }
                return new Response.Builder().type(ResponseType.CHILDREN).data(childrenDTO).build();
            } catch (ServiceException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        return null;
    }


    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }
    }

    @Override
    public void updateEnrollmentsNotify(Enrollment e) throws ServiceException {
        EnrollmentDTO enrollmentDTO = DTOUtils.getEnrollmentDTO(e);
        Response resp = new Response.Builder().type(ResponseType.ENROLLMENT_ADDED).data(enrollmentDTO).build();
        try {
            sendResponse(resp);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
