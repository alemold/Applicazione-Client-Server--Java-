import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.BindException;

public class Server {
    ServerSocket connection;
    Socket clientSocket;
    Socket client;
    int porta;
    InputStream is;
    OutputStream os;
    int timeout = 10000;  

    
    public Server(int porta){
        this.porta = porta;
        try {
            connection = new ServerSocket(porta);
            connection.setSoTimeout(timeout);
        } catch(BindException be) {
            System.err.println("Il server è già in ascolto!");
        } catch(IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void multiClient() throws IOException {
        while(true) {
            try {
                client = connection.accept();
                System.out.println("Connessione effettuata con: " + client.getInetAddress());
                GestoreClient gestore = new GestoreClient(client); 
                gestore.start();
            } catch(SocketTimeoutException s) {
                System.err.println("Timeout scaduto!");
                connection.close();
                break;
            }
            
        }

    }

    public void attendi(){
        try {
            clientSocket = connection.accept(); //creazione oggetto socket GestoreG(clientSocket)

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leggi(){
        String messaggioRicevuto;
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            messaggioRicevuto = br.readLine();
            System.out.println("CLIENT: " + messaggioRicevuto);
            System.out.println(clientSocket.getLocalSocketAddress()); 
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void rispondi(){
        String messaggio="";
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            messaggio = br.readLine();
        } catch (IOException ex) {
            System.err.println("Errore input/output");
        }
    }
        
}