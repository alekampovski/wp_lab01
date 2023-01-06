package mk.ukim.finki.wp.lab.repository.inMemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.exception.NoSuchStudentException;
import mk.ukim.finki.wp.lab.model.exception.NotDefinedObjectException;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class InMemoryStudentRepository {

    public List<Student> findAllStudents() {
        return DataHolder.studentList;
    }

    public Student findStudentByUsername(String username) {
        return DataHolder.studentList.stream().filter(student -> student.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NoSuchStudentException(username));
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return DataHolder.studentList.stream()
                .filter(student -> student.getName().contains(text) ||
                        student.getSurname().contains(text)).collect(Collectors.toList());
    }
    public Student save(Student student) {
        if (Objects.isNull(student)) {
            throw new NotDefinedObjectException();
        }
        DataHolder.studentList.add(student);
        return student;
    }
}
