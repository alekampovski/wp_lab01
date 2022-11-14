package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (Strings.isNotEmpty(error)) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("courses", courseService.findAll());
        return "listCourses";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacherId) {
        try {
            courseService.save(name, description, teacherId);
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
        return "redirect:/courses";
    }

    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "listCourses";
    }
}
