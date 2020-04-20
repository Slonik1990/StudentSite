package service;

import model.Student;
import repository.DataAtFile;
import repository.DataMaster;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



public class Creator {

    DataMaster dataMaster;

    public Creator(DataMaster dataMaster) {
        this.dataMaster = dataMaster;
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        //считывание параметров из HTTP запроса и создание из них студента
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Integer mark = Integer.parseInt(req.getParameter("mark"));
        Student toAdding = new Student(lastName, firstName, mark);

        //получение из коннектора списка студентов, добавление студента, запись через коннектор в файл

        List<Student> list = dataMaster.getData();

        if (list.contains(toAdding)) {
            resp.getWriter().println("<h2 align='center'>" + "Студент уже добавлен" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>"
            );
        } else {
            list.add(toAdding);
            dataMaster.putData(list);
            resp.getWriter().println("<h2 align='center'>" + "Добавлен студент: " + toAdding + "</H2>"+
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        }
    }
}


