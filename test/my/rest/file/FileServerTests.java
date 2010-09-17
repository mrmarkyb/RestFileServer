package my.rest.file;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.OptionsMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.internal.matchers.IsCollectionContaining.hasItems;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Jul 1, 2010
 * Time: 7:38:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileServerTests {
    private String serverLocation = "http://localhost:9090";
    private TheServer server;
    private int actualResponseCode;
    private HttpMethodBase method;

    @Before
    public void before() {
        startServer();
    }

    @After
    public void after() {
        server.stop();
    }

    private void startServer() {
        server = new TheServer(9090);
        server.start();
    }

    @Test
    public void shouldCreateNewFileForPostToDirectory() {
        givenADirectory("default").thatIsEmpty();
        whenIPostTo("/default");
        thenDirectory("default").containsOnly("new.txt");
    }

    @Test
    public void shouldContinueCreatingNewFilesOnFurtherPostsToDirectory() {
        givenADirectory("default").containingOnly("new.txt");
        whenIPostTo("/default");
        thenDirectory("default").containsOnly("new.txt", "new2.txt");
    }

    @Test
    public void shouldGetPostOptionsForDirectory() {
        givenADirectory("default").thatIsEmpty();
        whenIGetOptions("/default");
        thenHeaderAllows("POST");
    }

    private void thenHeaderAllows(String method) {
        assertThat(responseHeader("ALLOW"), containsString(method));
    }

    private String responseHeader(String headerName) {
        return method.getResponseHeader(headerName).getValue();
    }


    @Test
    public void shouldReturn302ForSuccessfulPost() {
        givenADirectory("default").thatIsEmpty();
        whenIPostTo("/default");
        thenIGetResponseCode(302);
    }

    @Test
    public void shouldGet404WhenPostingToADirectoryThatDoesNotExist() {
        givenADirectory("phantom").thatDoesNotExist();
        whenIPostTo("/phantom");
        thenIGetResponseCode(404);
    }

    private void thenIGetResponseCode(int expectedResponseCode) {
        assertThat(responseBody(), actualResponseCode, equalTo(expectedResponseCode));
    }

    private String responseBody() {
        try {
            return method.getResponseBodyAsString();
        } catch (IOException e) {
            fail(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void whenIPostTo(String path) {
        method = new PostMethod(serverLocation + path);
        executeMethod();
    }

    private void whenIGetOptions(String path) {
        method = new OptionsMethod(serverLocation + path);
        executeMethod();
    }

    private void executeMethod() {
        HttpClient client = new HttpClient();
        try {
            actualResponseCode = client.executeMethod(method);

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }


    private static ThenDirectory thenDirectory(String path) {
        return new ThenDirectory(path);
    }

    private static GivenDirectory givenADirectory(String path) {
        return new GivenDirectory(path);
    }


    private static class GivenDirectory {
        private File directory;

        public GivenDirectory(String path) {
            directory = new File(path);
            directory.mkdirs();
        }

        public void thatIsEmpty() {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }

        public void containingOnly(String filename) {
            thatIsEmpty();
            try {
                new File(directory, filename).createNewFile();
            } catch (IOException e) {
                fail(e.getMessage());
            }
        }

        public void thatDoesNotExist() {
            if (directory.exists()) {
                directory.delete();
            }
        }
    }

    private static class ThenDirectory {
        private File directory;

        public ThenDirectory(String path) {
            directory = new File(path);
        }

        public void containsOnly(String... filenames) {
            assertThat(asList(directory.list()), hasItems(filenames));
        }
    }
}
