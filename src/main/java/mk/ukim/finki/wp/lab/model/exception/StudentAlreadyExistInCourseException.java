package mk.ukim.finki.wp.lab.model.exception;

public class StudentAlreadyExistInCourseException extends RuntimeException{


    public StudentAlreadyExistInCourseException(String username) {
        super(String.format("Student with username [%s] already exist in Course list!", username));
    }
}
