package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.utils.Utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfEmployment;

    public Teacher(String name, String surname, LocalDate dateOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.dateOfEmployment = dateOfEmployment;
    }
}
