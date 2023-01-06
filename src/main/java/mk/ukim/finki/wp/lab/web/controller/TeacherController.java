package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.Type;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getTeachersPage(@RequestParam(required = false) String error, Model model) {
        if (Strings.isNotEmpty(error)) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Teacher> teachers = teacherService.findAll();
        teachers.sort(Comparator.comparing(Teacher::getName));
        model.addAttribute("teachers", teachers);
        return "show-teachers";
    }

    @GetMapping("/add-form")
    public String addCoursePage() {
        return "add-teacher";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfEmployment) {
        try {
            if (Objects.nonNull(id)) {
                teacherService.edit(id, name, surname, dateOfEmployment);
            } else {
                teacherService.save(name, surname, dateOfEmployment);
            }
        } catch (RuntimeException e) {
            return "redirect:/teachers?error=" + e.getMessage();
        }
        return "redirect:/teachers";
    }

    @GetMapping("/edit-form/{id}")
    public String editCoursePage(@PathVariable Long id, Model model) {
        try {
            Optional<Teacher> teacher = teacherService.findById(id);
            if (teacher.isEmpty()) {
                String message = String.format("Teacher with id [%d] does not exist!", id);
                return "redirect:/teachers?error=" + message;
            }
            model.addAttribute("teacher", teacher.get());
            return "add-teacher";
        } catch (RuntimeException e) {
            return "redirect:/teachers?error=" + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}
