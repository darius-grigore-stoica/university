package org.example.utils;

import java.net.Socket;
import java.util.Properties;

public abstract class AbstractConcurrentServer extends AbstractServer{

    public AbstractConcurrentServer(int PORT) {
        super(PORT);
        System.out.println("Concurrent server stated");
    }

    @Override
    protected void processRequest(Socket client) {
        Thread tw = createWorker(client);
        tw.start();
    }

    protected abstract Thread createWorker(Socket client);
}
