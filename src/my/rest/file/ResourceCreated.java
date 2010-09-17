package my.rest.file;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 6, 2010
 * Time: 7:35:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceCreated implements ActionResult {
    private String path;

    public ResourceCreated(String path) {
        this.path = path;
    }

    public void update(HttpServletResponse response) {
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            throw new RuntimeException("could not update response for successful create of " + path, e);
        }
    }
}
