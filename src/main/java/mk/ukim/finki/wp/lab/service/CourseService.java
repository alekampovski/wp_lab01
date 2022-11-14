package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService{

    List<Course> findAll();

    Course findById(Long courseId);
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    void addCoursesToStudents();
    Optional<Course> save(String name, String description, Long teacherId);

    void deleteById(Long id);
}

