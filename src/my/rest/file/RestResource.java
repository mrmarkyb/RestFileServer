package my.rest.file;

import sun.misc.CRC16;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 2, 2010
 * Time: 7:35:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RestResource {
    ActionResult post();

    ActionResult options();
}
