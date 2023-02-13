import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.BindException;

public class Server {
    ServerSocket connection;
    Socket clientSocket;
    int porta;
    InputStream is;
    OutputStream os;
    
    public Server(int porta){
        this.porta = porta;
        try {
            connection = new ServerSocket(porta);
        } catch(BindException be) {
            System.err.println("Il server è già in ascolto!");
        } catch(IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void multiClient() throws IOException {
        while(true) {
            Socket client = connection.accept();
            System.out.println("Connessione effettuata con: " + client.getInetAddress());
            GestoreClient gestore = new GestoreClient(client); //FIXA STO CODICE NON MI RICORDO CHE E' SU CHATGPT
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