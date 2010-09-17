package my.rest.file;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 2, 2010
 * Time: 7:36:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class DirectoryRestResource implements RestResource {
    private File file;

    public DirectoryRestResource(String path) {
        file = new File(path);
    }

    public ActionResult post() {
        try {
            File file = new File(this.file, getNewFilename());
            file.createNewFile();
            return new ResourceCreated(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    private String getNewFilename() {
        String filename = "new.txt";
        for(int i=1; filenamePresent(filename);i++) {
            filename = String.format("new%d.txt", i+1);
        }
        return filename;
    }

    private boolean filenamePresent(String filename) {
        return asList(file.list()).contains(filename);
    }
}
