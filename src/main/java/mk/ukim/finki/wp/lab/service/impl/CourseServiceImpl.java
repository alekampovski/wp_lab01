package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Course findById(Long courseId) {
        if (Objects.nonNull(courseId)) {
            return this.courseRepository.findById(courseId);
        } else throw new IllegalArgumentException();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if (Objects.nonNull(courseId)) {
            return this.courseRepository.findAllStudentsByCourse(courseId);
        } else throw new IllegalArgumentException();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if (Strings.isEmpty(username) && Objects.isNull(courseId)) {
            throw new IllegalArgumentException();
        }
            Course course = this.courseRepository.findById(courseId);
        Student student;
        try {
            student = studentRepository.findStudentByUsername(username);
        } catch (Exception e) {
            return null;
        }
        return this.courseRepository.addStudentToCourse(student, course);

    }
}
