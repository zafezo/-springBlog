package project.core.services.exceptions;

/**
 * Created by swen on 5/2/16.
 */
public class AccountExsistsException extends RuntimeException {
    public AccountExsistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExsistsException(String message) {
        super(message);
    }

    public AccountExsistsException() {
        super();
    }
}
