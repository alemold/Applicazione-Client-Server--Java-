public class MainServer {

    public static void main(String[] args) {
        Server s = new Server(2000);
        if (s != null) {
                s.attendi();
                s.leggi();
                //s.chiudi();
            }
    }
}