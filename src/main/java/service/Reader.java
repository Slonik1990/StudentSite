package service;

import model.Student;
import repository.DataAtFile;
import repository.DataMaster;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Reader{
    DataMaster dataMaster;

    public Reader(DataMaster dataMaster) {
        this.dataMaster = dataMaster;
    }

    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");


        //считывание параметров из HTTP запроса
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");

        //из не пустых параметров создается лист
        List notNullParameters = new ArrayList();
        if (lastName.length() > 0) notNullParameters.add(lastName);
        if (firstName.length() > 0) notNullParameters.add(firstName);
        if (req.getParameter("mark").length() > 0) {
            Integer mark = Integer.parseInt(req.getParameter("mark"));
            notNullParameters.add(mark);
        }


        //студенты преобразуются в листы с параметрами и происходит сравнение с пришедшими параметрами
        List<Student> everyBody = dataMaster.getData();
        List<Student> suitable = new ArrayList<>();
        for (Student s : everyBody) {
            List studFields = new ArrayList();
            studFields.add(s.getLastName());
            studFields.add(s.getFirstName());
            studFields.add(s.getMark());
            if (studFields.containsAll(notNullParameters)) suitable.add(s);
        }


        if (suitable.size() == 0) {
            resp.getWriter().println("<h2 align='center'>" + "Поиск не дал результата" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
            return;
        }

        String report = "";
        for (Student s : suitable) {
            report = report.concat("<h2 align='center'>" + s.toString() + "</h2>");
        }

        resp.getWriter().println(report +"<form align='center' action='index.html'>" +
                "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                "</form>");
    }

    public void readAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<Student> everyBody = dataMaster.getData();

        if (everyBody.size() == 0) {
            resp.getWriter().println("<h2 align='center'>" + "Поиск не дал результата" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
            return;
        }

        String report = "";
        for (Student s : everyBody) {
            report = report.concat("<h2 align='center'>" + s.toString() + "</h2>");
        }

        resp.getWriter().println(report + "<form align='center' action='index.html'>" +
                "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                "</form>");
    }

}



