package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface StudentService{

    public void create(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void read(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void readAll(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
