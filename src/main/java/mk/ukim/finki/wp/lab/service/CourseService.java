package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enumeration.Type;

import java.util.List;
import java.util.Optional;

public interface CourseService{

    List<Course> findAll();

    Optional<Course> findById(Long courseId);
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    //void addCoursesToStudents();
    Optional<Course> save(String name, String description, Long teacherId, Type type);

    void deleteById(Long id);
    Optional<Course> edit(Long id, String name, String description, Long teacherId, Type type);
}

