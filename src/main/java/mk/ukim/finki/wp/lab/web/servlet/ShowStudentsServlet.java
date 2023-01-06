package mk.ukim.finki.wp.lab.web.servlet;

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

@Profile("servlets")
@WebServlet(name = "show-student-servlet", urlPatterns = "/showStudents")
public class ShowStudentsServlet extends HttpServlet {
    private final StudentService studentService;
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowStudentsServlet(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        //this.courseService.addCoursesToStudents();
        webContext.setVariable("students",  this.studentService.listAll());
        springTemplateEngine.process("showStudents.html", webContext, resp.getWriter());
    }
}
