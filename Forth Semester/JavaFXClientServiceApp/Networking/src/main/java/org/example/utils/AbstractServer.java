package org.example.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;

public abstract class AbstractServer {

    private int PORT;
    private ServerSocket server = null;

    public AbstractServer(int PORT) {
        System.out.println("Created server with port " + PORT);
        this.PORT = PORT;
    }

    public void start() {

        try {
            server = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for clients ...");
                Socket client = server.accept();
                System.out.println("Client connected ...");
                processRequest(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void processRequest(Socket client);

    public void stop() throws ServerException {
        try {
            server.close();
        } catch (IOException e) {
            throw new ServerException("Closing server error ", e);
        }
    }
}
