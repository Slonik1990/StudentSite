import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeleteStudent extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FileConnector fileConnector = new FileConnector();
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        //считывание параметров из HTTP запроса и создание из них студента
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        Student toRemove = new Student(lastName, firstName);

        List<Student> everyBody = fileConnector.getData();

        if (everyBody.contains(toRemove)) {
            everyBody.remove(toRemove);
            fileConnector.putData(everyBody);
            resp.getWriter().println("<h2 align='center'>" + "Студент удален"+ "</H2>");
        } else {
            resp.getWriter().println("<h2 align='center'>" + "Студент не найден" +"</H2>");
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        FileConnector fileConnector = new FileConnector();
        List<Student> nobody = new LinkedList<>();
        fileConnector.putData(nobody);
        resp.getWriter().println("<h2 align='center'>" + "СПИСОК СТУДЕНТОВ ОЧИЩЕН" +"</H2>");
    }
}
