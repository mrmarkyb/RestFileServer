package my.rest.file;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 6, 2010
 * Time: 7:37:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceDoesNotExist implements ActionResult {
    private String path;

    public ResourceDoesNotExist(String path) {
        this.path = path;
    }

    public void update(HttpServletResponse response) {
        try {
            response.sendError(404, String.format("%s does not exist", path));
        } catch (IOException e) {
            throw new RuntimeException("failed to write response", e);
        }
    }
}
