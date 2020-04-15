import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FileConnector fileConnector = new FileConnector();
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Integer mark = Integer.parseInt(req.getParameter("mark"));
        Student toChangeMark = new Student(lastName, firstName, mark);

        List<Student> everyBody = fileConnector.getData();

        if (everyBody.contains(toChangeMark)) {
            everyBody.remove(toChangeMark);
            everyBody.add(toChangeMark);
            fileConnector.putData(everyBody);
            resp.getWriter().println("<h2 align='center'>" + "Оценка изменена"+ "</H2>");
        } else {
            resp.getWriter().println("<h2 align='center'>" + "Студент не найден" +"</H2>");
        }
    }
}
