package mk.ukim.finki.wp.lab.model.exception;

public class StudentNotContainedException extends RuntimeException {
    public StudentNotContainedException() {
        super("Student is not attending at any of given courses!");
    }
}
