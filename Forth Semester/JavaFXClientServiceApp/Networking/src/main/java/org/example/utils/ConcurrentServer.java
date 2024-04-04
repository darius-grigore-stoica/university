package org.example.utils;

import org.example.IObserver;
import org.example.IService;
import org.example.protocol.ClientWorker;

import java.net.Socket;

public class ConcurrentServer extends AbstractConcurrentServer{

    IService service;

    public ConcurrentServer(int PORT, IService service) {
        super(PORT);
        this.service = service;
        System.out.println("Concurrent server created");
    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientWorker worker = new ClientWorker(service, client);

        return new Thread(worker);
    }

    @Override
    public void stop() {
        System.out.println("Stopping concurrent server");
    }
}
