package servlet;

import repository.DataAtList;
import repository.DataMaster;
import service.Creator;
import service.Deleter;
import service.Reader;
import service.Updater;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActionServlet extends HttpServlet {

    private DataMaster dataMaster;

    //следующие компоненты могли бы быть одним классом, содержащим в себе все методы,  но для обучения разделены на отдельные компоненты
    private Creator creator;
    private Deleter deleter;
    private Updater updater;
    private Reader reader;


    @Override
    public void init() throws ServletException {
        System.out.println("________________init________________");
        this.dataMaster = new DataAtList();
        this.creator = new Creator(dataMaster);
        this.deleter = new Deleter(dataMaster);
        this.updater = new Updater(dataMaster);
        this.reader = new Reader(dataMaster);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String task = req.getParameter("task");
        switch (task){
            case "read":
                reader.read(req, resp);
                break;
            case "readAll":
                reader.readAll(req, resp);
                break;
            case "create":
                creator.create(req, resp);
                break;
            case "update":
                updater.update(req, resp);
                break;
            case "delete":
                deleter.delete(req, resp);
                break;
            case "deleteAll":
                deleter.deleteAll(req, resp);
                break;
        }

    }


}
