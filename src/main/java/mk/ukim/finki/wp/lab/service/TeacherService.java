package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Optional<Teacher> save(String name, String surname, LocalDate dateOfEmployment);
    void deleteById(Long id);

    Optional<Teacher> edit(Long id, String name, String surname, LocalDate dateOfEmployment);
}
