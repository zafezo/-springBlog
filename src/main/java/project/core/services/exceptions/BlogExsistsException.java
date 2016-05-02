package project.core.services.exceptions;

/**
 * Created by swen on 5/2/16.
 */
public class BlogExsistsException  extends RuntimeException {
    public BlogExsistsException() {
        super();
    }

    public BlogExsistsException(String message) {
        super(message);
    }

    public BlogExsistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
