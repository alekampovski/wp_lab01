package mk.ukim.finki.wp.lab.repository.inMemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTeacherRepository {
    public List<Teacher> findAll() {
        return DataHolder.teachers;
    }

    public Optional<Teacher> findById(Long id) {
        return DataHolder.teachers.stream()
                .filter(teacher -> teacher.getId().equals(id))
                .findFirst();
    }
}
