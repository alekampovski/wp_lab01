package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if(Strings.isEmpty(text)) {
            throw new InvalidArgumentsException();
        }
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if (Strings.isEmpty(username) || Strings.isEmpty(password) ||
        Strings.isEmpty(name) && Strings.isEmpty(surname)) {
            throw new InvalidArgumentsException();
        }
        Student student = new Student(username, password, name, surname);
        return studentRepository.save(student);
    }
}
