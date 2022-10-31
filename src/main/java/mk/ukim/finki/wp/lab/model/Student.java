package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String username;
    private String password;
    private String name;
    private String surname;

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }

    public String getFullNameAndUserName() {
        return String.format("%s (%s)", getFullName(), username);
    }
}
