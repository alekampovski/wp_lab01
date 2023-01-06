package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/add-form")
    public String createStudent() {
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String username,
                             @RequestParam String password) {
        try {
            studentService.save(username, password, name, surname);
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
        return "redirect:/courses";
    }
}
