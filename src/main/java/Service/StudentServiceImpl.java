package Service;

import Model.Student;
import Repository.DataMaster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class StudentServiceImpl implements StudentService {

    DataMaster dataMaster;

    public StudentServiceImpl(DataMaster dataMaster) {
        this.dataMaster = dataMaster;
    }

    @Override
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
                    "<form align='center' action='create.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                    "</form>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>"
            );
        } else {
            list.add(toAdding);
            dataMaster.putData(list);
            resp.getWriter().println("<h2 align='center'>" + "Добавлен студент: " + toAdding + "</H2>" +
                    "<form align='center' action='create.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                    "</form>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        }
    }

    @Override
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

        if (notNullParameters.size() == 0) {
            resp.getWriter().println("<h2 align='center'>" + "Необходимо заполнить минимум 1 поле" + "</H2>" +
                    "<form align='center' action='read.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                    "</form>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
            return;
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
                    "<form align='center' action='read.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                    "</form>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
            return;
        }

        String report = "";
        for (Student s : suitable) {
            report = report.concat("<h2 align='center'>" + s.toString() + "</h2>");
        }

        resp.getWriter().println(
                report +
                        "<form align='center' action='read.html'>" +
                        "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                        "</form>" + "<form align='center' action='index.html'>" +
                        "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                        "</form>");
    }

    @Override
    public void readAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<Student> everyBody = dataMaster.getData();

        if (everyBody.size() == 0) {
            resp.getWriter().println("<h2 align='center'>" + "Поиск не дал результата" + "</H2>" +
                    "<form align='center' action='read.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                    "</form>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
            return;
        }

        String report = "";
        for (Student s : everyBody) {
            report = report.concat("<h2 align='center'>" + s.toString() + "</h2>");
        }

        resp.getWriter().println(
                report + "<form align='center' action='read.html'>" +
                        "<p><button  style='font-size: 20px; height: 40px; width: 200px'>Назад</button></p>" +
                        "</form>" +
                        "<form align='center' action='index.html'>" +
                        "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                        "</form>");
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Integer mark = Integer.parseInt(req.getParameter("mark"));
        Student toChangeMark = new Student(lastName, firstName, mark);

        List<Student> everyBody = dataMaster.getData();

        if (everyBody.contains(toChangeMark)) {
            everyBody.remove(toChangeMark);
            everyBody.add(toChangeMark);
            dataMaster.putData(everyBody);
            resp.getWriter().println("<h2 align='center'>" + "Оценка изменена" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        } else {
            resp.getWriter().println("<h2 align='center'>" + "Студент не найден" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        }
    }

    @Override
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
            resp.getWriter().println("<h2 align='center'>" + "Студент удален" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        } else {
            resp.getWriter().println("<h2 align='center'>" + "Студент не найден" + "</H2>" +
                    "<form align='center' action='index.html'>" +
                    "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                    "</form>");
        }
    }

    @Override
    public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<Student> nobody = new ArrayList<>();
        dataMaster.putData(nobody);
        resp.getWriter().println("<h2 align='center'>" + "СПИСОК СТУДЕНТОВ ОЧИЩЕН" + "</H2>" +
                "<form align='center' action='index.html'>" +
                "<p><button  style='font-size: 20px; height: 40px; width: 200px'>На главную</button></p>" +
                "</form>");
    }
}



