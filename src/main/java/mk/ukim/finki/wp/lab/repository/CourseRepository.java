package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.NoSuchCourseException;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Course> isCourseListenedByStudent(Student student) {
        if (Objects.nonNull(student)) {
            return DataHolder.courseList.stream()
                    .filter(course -> course.getStudents().contains(student))
                    .collect(Collectors.toList());
        } return new ArrayList<>();
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

    public Optional<Course> save(String name, String description, Teacher teacher) {
        DataHolder.courseList.removeIf(course -> course.getName().equals(name));
        Course course = new Course(name, description, teacher);
        DataHolder.courseList.add(course);
        return Optional.of(course);
    }

    public void deleteById(Long id) {
        DataHolder.courseList.removeIf(course -> course.getCourseId().equals(id));
    }
}
