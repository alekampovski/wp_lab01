package mk.ukim.finki.wp.lab.model.exception;

public class NoSuchStudentException extends RuntimeException {

    public NoSuchStudentException() {}
    public NoSuchStudentException(String message) {
        super(message);
    }

}
