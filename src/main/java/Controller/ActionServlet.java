package Controller;

import Repository.DataMaster;
import Service.StudentService;
import Service.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionServlet extends HttpServlet {


    private StudentService studentService;



    @Override
    public void init() throws ServletException {
        System.out.println("________________init________________");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        this.studentService = applicationContext.getBean(StudentService.class);


    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String task = req.getParameter("task");
        switch (task){
            case "read":
                studentService.read(req, resp);
                break;
            case "readAll":
                studentService.readAll(req, resp);
                break;
            case "create":
                studentService.create(req, resp);
                break;
            case "update":
                studentService.update(req, resp);
                break;
            case "delete":
                studentService.delete(req, resp);
                break;
            case "deleteAll":
                studentService.deleteAll(req, resp);
                break;
        }

    }


}
