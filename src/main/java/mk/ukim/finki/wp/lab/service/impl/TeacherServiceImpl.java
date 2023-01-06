package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.NoSuchCourseException;
import mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        if (Objects.nonNull(id)) {
            return this.teacherRepository.findById(id);
        } else throw new IllegalArgumentException();
    }

    @Override
    public Optional<Teacher> save(String name, String surname, LocalDate dateOfEmployment) {
        return Optional.of(teacherRepository.save(new Teacher(name, surname, dateOfEmployment)));
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Optional<Teacher> edit(Long id, String name, String surname, LocalDate dateOfEmployment) {
        Optional<Teacher> teacher = findById(id);
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException(id);
        }
        Teacher teacherObj = teacher.get();
        teacherObj.setName(name);
        teacherObj.setSurname(surname);
        teacherObj.setDateOfEmployment(dateOfEmployment);
        return Optional.of(teacherRepository.save(teacherObj));
    }
}
