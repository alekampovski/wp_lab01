package mk.ukim.finki.wp.lab.model.exception;

public class NoSuchCourseException extends RuntimeException{

    public NoSuchCourseException() {}
    public NoSuchCourseException(Long courseId) {
        super(String.format("Course with Id %s does not exist!", courseId));
    }
}
