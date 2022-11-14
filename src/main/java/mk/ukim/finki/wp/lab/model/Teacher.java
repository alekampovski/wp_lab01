package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.utils.Utils;

@Data
public class Teacher {
    private Long id;
    private String name;
    private String surname;

    public Teacher(String name, String surname) {
        this.id = Utils.generateRandomId();
        this.name = name;
        this.surname = surname;
    }
}
