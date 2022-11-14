package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();

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

        //adding teachers

        teachers.add(new Teacher("teacherName01", "teacherSurname01"));
        teachers.add(new Teacher("teacherName02", "teacherSurname02"));
        teachers.add(new Teacher("teacherName03", "teacherSurname03"));
        teachers.add(new Teacher("teacherName04", "teacherSurname04"));
        teachers.add(new Teacher("teacherName05", "teacherSurname05"));


        courseList.add(new Course("courseName01", "description01", studentsListCourse01, teachers.get(0)));
        courseList.add(new Course("courseName02", "description02", teachers.get(1)));
        courseList.add(new Course("courseName03", "description03", teachers.get(2)));
        courseList.add(new Course("courseName04", "description04", teachers.get(3)));
        courseList.add(new Course("courseName05", "description05", teachers.get(4)));
    }
}
