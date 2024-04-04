package org.example.protocol;

import org.example.IObserver;
import org.example.IService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientWorker implements Runnable, IObserver {
    private IService server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private volatile boolean connected;

    public ClientWorker(IService server, Socket connection) {
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
            } catch (Exception e) {
                System.out.println("Error processing request " + e);
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

    private Response handleRequest(Request request) {
        switch (request.type()) {
            case LOGIN -> {
                String username = (String) request.data();
                String password = (String) request.data();
                try {
                    server.login(username, password);
                    return okResponse;
                } catch (Exception e) {
                    connected = false;
                    return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
                }
            }

            case LOGOUT -> {
                String username = (String) request.data();
                try {
                    server.logout(username);
                    connected = false;
                    return okResponse;
                } catch (Exception e) {
                    return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
                }
            }
            case ENROLL -> {
                // TODO
                return null;
            }

            case SEARCH -> {
                // TODO
                return null;
            }

            case GET_ALL_COMPETITIONS -> {
                try {
                    server.getAllCompetitions();
                    return new Response.Builder().type(ResponseType.COMPETITIONS).data(server.getAllCompetitions()).build();
                } catch (Exception e) {
                    return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
                }

            }

            case GET_ALL_ENROLLMENTS -> {
                try {
                    return new Response.Builder().type(ResponseType.ENROLLMENTS).data(server.getAllEnrollments()).build();
                } catch (Exception e) {
                    return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
                }
            }

            case GET_ALL_CHILDREN -> {
                try {
                    return new Response.Builder().type(ResponseType.CHILDREN).data(server.getAllChildren()).build();
                } catch (Exception e) {
                    return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
                }
            }
        }
        return null;
    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private void sendResponse(Response response) throws IOException {
        output.writeObject(response);
        output.flush();
    }
}
