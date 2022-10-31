package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.exception.NoSuchCourseException;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class CourseRepository {
    List<Course> findAllCourses() {
        return DataHolder.courseList;
    }

    public Course findById(Long courseId) {
        if (Objects.nonNull(courseId)) {
            return DataHolder.courseList.stream()
                    .filter(course -> course.getCourseId().equals(courseId))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchCourseException(String.format("Course with Id %s does not exist!", courseId)));
        }
        return null;
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        Course c = findById(courseId);
        if (Objects.nonNull(c)) {
            return c.getStudents();
        }
        return null;
    }

    public Course addStudentToCourse(Student student, Course course) {
        if (Objects.nonNull(student) && Objects.nonNull(course)) {
            course.getStudents().add(student);
        return course;
        }
        return null;
    }

    public List<Course> findAll() {
        return DataHolder.courseList;
    }
}
