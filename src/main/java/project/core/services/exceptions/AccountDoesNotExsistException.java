package project.core.services.exceptions;

/**
 * Created by swen on 5/2/16.
 */
public class AccountDoesNotExsistException extends RuntimeException {
    public AccountDoesNotExsistException() {
        super();
    }

    public AccountDoesNotExsistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExsistException(String message) {
        super(message);
    }
}
