package mk.ukim.finki.wp.lab.model.exception;

public class NoSuchStudentException extends RuntimeException {

    public NoSuchStudentException() {}
    public NoSuchStudentException(String username) {
        super(String.format("Student with username [%s] does not exist!", username));
    }

}
