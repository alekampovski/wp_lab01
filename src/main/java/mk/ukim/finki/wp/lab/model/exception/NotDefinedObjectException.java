package mk.ukim.finki.wp.lab.model.exception;

public class NotDefinedObjectException extends RuntimeException {
    public NotDefinedObjectException() {
        super("Passed object is null!");
    }
}
