package my.rest.file;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 6, 2010
 * Time: 7:35:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileRestResource implements RestResource{
    private File file;

    public FileRestResource(File file) {
        this.file = file;
    }

    public ActionResult post() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
