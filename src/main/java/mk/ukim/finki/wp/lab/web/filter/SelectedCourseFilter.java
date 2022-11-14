package mk.ukim.finki.wp.lab.web.filter;

import mk.ukim.finki.wp.lab.model.Course;
import org.apache.logging.log4j.util.Strings;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter
public class SelectedCourseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();

        String courseId = request.getParameter("courseId");
        Course course = (Course) request.getSession().getAttribute("course");

        if(!"/listCourses".equals(path) && !"/courses".equals(path) && !("/AddStudent".equals(path) && Objects.nonNull(courseId)) && !"/showStudents".equals(path) && Objects.isNull(course)) {
            response.sendRedirect("/listCourses");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
