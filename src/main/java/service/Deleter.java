package service;

import model.Student;
import repository.DataMaster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Deleter {
    DataMaster dataMaster;

    public Deleter(DataMaster dataMaster) {
        this.dataMaster = dataMaster;
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        //считывание параметров из HTTP запроса и создание из них студента
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Student toRemove = new Student(lastName, firstName);

        List<Student> everyBody = dataMaster.getData();

        if (everyBody.contains(toRemove)) {
            everyBody.remove(toRemove);
            dataMaster.putData(everyBody);
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


    public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<Student> nobody = new ArrayList<>();
        dataMaster.putData(nobody);
        resp.getWriter().println("<h2 align='center'>" + "СПИСОК СТУДЕНТОВ ОЧИЩЕН" +"</H2>" +
                "<form align='center' action='index.html'>" +
                "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                "</form>");
    }
}
