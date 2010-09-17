package my.rest.file;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 5, 2010
 * Time: 7:50:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoResource implements RestResource {
    private String path;

    public NoResource(String path) {
        this.path = path;
    }

    public ActionResult post() {
        return new ResourceDoesNotExist(path);
    }
}
