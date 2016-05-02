package project.core.services.exceptions;

/**
 * Created by swen on 5/2/16.
 */
public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException() {
        super();
    }

    public BlogNotFoundException(String message) {
        super(message);
    }

    public BlogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
