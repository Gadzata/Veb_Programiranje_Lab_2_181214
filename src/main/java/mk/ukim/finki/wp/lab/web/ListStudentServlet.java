package mk.ukim.finki.wp.lab.web;


import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "student-list", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Student> listi = this.studentService.listAll();
        context.setVariable("listStudents", this.studentService.listAll());
        context.setVariable("courseIdLong", Long.parseLong((String) req.getSession().getAttribute("courseId")));
        String id = (String) req.getSession().getAttribute("courseId");
        context.setVariable("courseId", id);
        this.springTemplateEngine.process("listStudents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentUsername = req.getParameter("studentId");
        String id = (String) req.getSession().getAttribute("courseId");
        req.getSession().setAttribute("studentUsername", studentUsername);
        req.getSession().setAttribute("courseID", id);

        resp.sendRedirect("/StudentEnrollmentSummary");
        return;
    }
}
