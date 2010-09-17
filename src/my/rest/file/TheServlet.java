package my.rest.file;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 1, 2010
 * Time: 8:04:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class TheServlet  extends HttpServlet {

    RestResourceRepository repository = new RestResourceRepository();

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResource resource = getResource(req.getPathInfo().substring(1));
        resource.options().update(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResource resource = getResource(req.getPathInfo().substring(1));
        resource.post().update(resp);
    }

    

    private RestResource getResource(String path) {
        return repository.getResource(path);
    }
}
