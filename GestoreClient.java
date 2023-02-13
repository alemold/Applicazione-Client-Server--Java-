import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GestoreClient implements Runnable {
    private Socket client;
    InputStream is;
    OutputStream os;

    public GestoreClient(Socket s) {
        this.client = s;
    }

    public void run() {
    }
}
