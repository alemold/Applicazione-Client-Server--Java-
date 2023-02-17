import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {
        Server s = new Server(2000);
        if (s != null) {
                s.attendi();
                s.multiClient(); //fixa questo codice
                s.leggi();
                //s.chiudi();
            }
    }
}