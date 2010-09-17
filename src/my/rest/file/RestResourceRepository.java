package my.rest.file;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 5, 2010
 * Time: 7:48:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestResourceRepository {

    RestResource getResource(String path) {
        if(new File(path).exists()) {
            return new DirectoryRestResource(path);
        }

        return new NoResource(path);
    }

}
