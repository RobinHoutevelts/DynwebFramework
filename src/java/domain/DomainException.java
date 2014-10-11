package domain;

public class DomainException extends Exception {

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }
    
    public DomainException(Throwable cause) {
        super(cause);
    }
    
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}