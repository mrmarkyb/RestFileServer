package my.rest.file;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 1, 2010
 * Time: 7:53:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class TheServer {
    private Server server;

    public TheServer(int port) {
        server = new Server(port);
        Context root = new Context(server,"/", Context.SESSIONS);
        root.addServlet(new ServletHolder(new TheServlet()), "/*");
    }

    public void start() {
                try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
