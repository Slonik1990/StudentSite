package servlets;

import model.Student;
import repository.DataFile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet
public class ChangeStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataFile dataFile = new DataFile();
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Integer mark = Integer.parseInt(req.getParameter("mark"));
        Student toChangeMark = new Student(lastName, firstName, mark);

        List<Student> everyBody = dataFile.getData();

        if (everyBody.contains(toChangeMark)) {
            everyBody.remove(toChangeMark);
            everyBody.add(toChangeMark);
            dataFile.putData(everyBody);
            resp.getWriter().println("<h2 align='center'>" + "Оценка изменена"+ "</H2>"+
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        } else {
            resp.getWriter().println("<h2 align='center'>" + "Студент не найден" +"</H2>"+
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        }
    }
}
