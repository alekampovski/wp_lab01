package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Profile("servlets")
@WebServlet(name = "student-servlet", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;


    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        Long courseId = Long.valueOf(req.getParameter("courseId"));
        Optional<Course> course = courseService.findById(courseId);
        if (course.isEmpty()) {
            resp.sendRedirect("/listCourses");
        }
        req.getSession().setAttribute("course", course.get());
        req.getSession().setAttribute("students", studentService.listAll());
        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }
}
