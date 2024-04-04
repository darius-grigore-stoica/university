package org.example;

public class ServiceException extends Exception{
    public ServiceException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
