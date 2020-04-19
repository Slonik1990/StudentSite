package servlets;

import model.Student;
import repository.DataAtFile;
import repository.DataAtList;
import repository.DataMaster;

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
    DataMaster dataMaster = new DataAtFile();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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


        //студенты преобразуются в листы с параметрами и происходит сравнение с пришедшими парамеирами
        List<Student> everyBody = dataMaster.getData();
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



