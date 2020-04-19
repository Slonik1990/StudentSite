package servlets;

import model.Student;
import repository.DataFile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@WebServlet
public class ReadStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        DataFile dataFile = new DataFile();

        //каждый студент из БД преобразуется в лист с его параметрами и производится проверка на наличие
        //всех не пустых параметров из HTTP запроса
        //данный подход позволяет одинаково обрабатывать любые комбинации параметров заданных в поиске


        //считывание параметров из HTTP запроса
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");

        //из не пустых параметров создается лист
        List notNullParameters = new LinkedList();
        if (lastName.length() > 0) notNullParameters.add(lastName);
        if (firstName.length() > 0) notNullParameters.add(firstName);
        if (req.getParameter("mark").length() > 0) {
            Integer mark = Integer.parseInt(req.getParameter("mark"));
            notNullParameters.add(mark);
        }


        //студенты преобразуются в листы с параметрами и происходит сравнение с пришедшими парамеирами
        List<Student> everyBody = dataFile.getData();
        List<Student> satisfied = new LinkedList<>();
        for (Student s : everyBody) {
            List studFields = new ArrayList();
            studFields.add(s.getLastName());
            studFields.add(s.getFirstName());
            studFields.add(s.getMark());
            if (studFields.containsAll(notNullParameters)) satisfied.add(s);
        }


        if (satisfied.size() == 0) {
            resp.getWriter().println("<h2 align='center'>" + "Поиск не дал результата" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
            return;
        }

        String report = "";
        for (Student s : satisfied) {
            report = report.concat("<h2 align='center'>" + s.toString() + "</h2>");
        }

        resp.getWriter().println(report +"<form align='center' action='index.html'>" +
                "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                "</form>");
    }

}



