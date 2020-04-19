package servlets;

import model.Student;
import repository.DataFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet
public class DeleteStudent extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataFile dataFile = new DataFile();
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        //считывание параметров из HTTP запроса и создание из них студента
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Student toRemove = new Student(lastName, firstName);

        List<Student> everyBody = dataFile.getData();

        if (everyBody.contains(toRemove)) {
            everyBody.remove(toRemove);
            dataFile.putData(everyBody);
            resp.getWriter().println("<h2 align='center'>" + "Студент удален"+ "</H2>"+
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



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        DataFile dataFile = new DataFile();
        List<Student> nobody = new LinkedList<>();
        dataFile.putData(nobody);
        resp.getWriter().println("<h2 align='center'>" + "СПИСОК СТУДЕНТОВ ОЧИЩЕН" +"</H2>" +
                "<form align='center' action='index.html'>" +
                "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                "</form>");
    }
}