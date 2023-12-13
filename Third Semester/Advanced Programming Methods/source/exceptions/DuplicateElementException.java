package exceptions;

public class DuplicateElementException extends Exception{

    private final String message;

    public DuplicateElementException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
