package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.wp.lab.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    public Course() {
        this.courseId = Utils.generateRandomId();
    }

    public Course(String name, String description, Teacher teacher) {
        this.courseId = Utils.generateRandomId();
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
    }

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.courseId = Utils.generateRandomId();
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
