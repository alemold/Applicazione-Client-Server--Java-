import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GestoreClient extends Thread {
    private Socket client;

    public GestoreClient(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            InputStream in = client.getInputStream();
            OutputStream os = client.getOutputStream();

            byte[] request = new byte[1024];
            int bytesRead = in.read(request);
            String requestStr = new String(request, 0, bytesRead);
            System.out.println("Richiesta ricevuta: " + requestStr);

            String response = "Risposta del server";
            os.write(response.getBytes());
            System.out.println("Inviato: " + response);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
