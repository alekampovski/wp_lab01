package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Student {
    private String username;
    private String password;
    private String name;
    private String surname;
    List<Course> courseList;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.courseList = new ArrayList<>();
    }

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }

    public String getFullNameAndUserName() {
        return String.format("%s (%s)", getFullName(), username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(username, student.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
