package mk.ukim.finki.wp.lab.model.exception;

public class NoSuchCourseException extends RuntimeException{

    public NoSuchCourseException() {}
    public NoSuchCourseException(String message) {
        super(message);
    }
}
