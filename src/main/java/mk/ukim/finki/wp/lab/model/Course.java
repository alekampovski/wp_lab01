package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enumeration.Type;
import mk.ukim.finki.wp.lab.utils.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    @ManyToMany
    private List<Student> students;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Teacher teacher;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
        this.type = type;
    }

    public Course(String name, String description, List<Student> students, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
        this.type = type;
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
