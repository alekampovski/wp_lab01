package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//todo- full text search
@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (Strings.isNotEmpty(error)) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Course> courses = courseService.findAll();
        courses.sort(Comparator.comparing(Course::getName));
        model.addAttribute("courses", courses);
        return "listCourses";
    }

    @GetMapping("/edit-form/{id}")
    public String editCoursePage(@PathVariable Long id, Model model) {
        try {
            Optional<Course> course = courseService.findById(id);
            if (course.isEmpty()) {
                String message = String.format("Course with id [%d] does not exist!", id);
                return "redirect:/courses?error=" + message;
            }
            List<Teacher> teachers = teacherService.findAll();
            model.addAttribute("teachers", teachers);
            model.addAttribute("course", course.get());
            return "add-course";
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
    }

    @GetMapping("/add-form")
    public String addCoursePage(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "add-course";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacher,
                             @RequestParam Type type) {
        try {
            if (Objects.nonNull(id)) {
                courseService.edit(id, name, description, teacher, type);
            } else {
                courseService.save(name, description, teacher, type);
            }
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("{id}/student-enrollment-summary")
    public String getStudents(@PathVariable Long id, Model model) {
        try {
            Optional<Course> course = courseService.findById(id);
            if (course.isEmpty()) {
                String message = "Course with id " + id + " does not exist!";
                return "redirect:/courses?error=" + message;
            }
            model.addAttribute("course", course.get());
            model.addAttribute("allStudents", studentService.listAll());
            return "list-students";
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
    }

    @PostMapping("/{id}/add-student")
    public String addStudent(@PathVariable Long id, @RequestParam String username) {
        try {
            courseService.addStudentInCourse(username, id);
        } catch (RuntimeException e) {
            return "redirect:/courses/" + id + "/student-enrollment-summary?error=" + e.getMessage();
        }
        return "redirect:/courses/" + id + "/student-enrollment-summary";
    }
}

//TODO:  is still referenced from table | delete button fix
//TODO: select course | in order to see students in course
//TODO: Create enrollment page.
//TODO: continue with the requirements from the exercise
