package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();

    @PostConstruct
    public void init() {
        studentList.add(new Student("user01", "password01", "name01", "surname01"));
        studentList.add(new Student("user02", "password02", "name02", "surname02"));
        studentList.add(new Student("user03", "password03", "name03", "surname03"));
        studentList.add(new Student("user04", "password04", "name04", "surname04"));
        studentList.add(new Student("user05", "password05", "name05", "surname05"));

        List<Student> studentsListCourse01 = new ArrayList<>();
        studentsListCourse01.add(studentList.get(2));
        studentsListCourse01.add(studentList.get(3));
        courseList.add(new Course(1L, "courseName01", "description01", studentsListCourse01));
        courseList.add(new Course(2L, "courseName02", "description02", new ArrayList<>()));
        courseList.add(new Course(3L, "courseName03", "description03", new ArrayList<>()));
        courseList.add(new Course(4L, "courseName04", "description04", new ArrayList<>()));
        courseList.add(new Course(5L, "courseName05", "description05", new ArrayList<>()));
    }
}
