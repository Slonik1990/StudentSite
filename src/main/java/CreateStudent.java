import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        //считывание параметров из HTTP запроса и создание из них студента
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Integer mark = Integer.parseInt(req.getParameter("mark"));
        Student toAdding = new Student(lastName, firstName, mark);

        //получение из коннектора списка студентов, добавление студента, запись через коннектор в файл
        FileConnector fileConnector = new FileConnector();
        List<Student> list = fileConnector.getData();

        if (list.contains(toAdding)) {
            resp.getWriter().println("<h2 align='center'>" + "Студент уже добавлен"+ "</H2>");
        } else {
            list.add(toAdding);
            fileConnector.putData(list);
            resp.getWriter().println("<h2 align='center'>" + "Добавлен студент: " + toAdding + "</H2>");
        }
    }
}



