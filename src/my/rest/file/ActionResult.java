package my.rest.file;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 5, 2010
 * Time: 7:52:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActionResult {
    void update(HttpServletResponse response);
}
