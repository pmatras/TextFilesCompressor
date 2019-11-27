package textfilescompressorserver.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class SingleServiceConnection implements Closeable {
    
    /**
     * socket - represents connection to the client
     */
    private Socket socket;
    /**
     * clientsInput - buffered input character stream
     */
    private BufferedReader clientsInput;
    /**
     * clientsOutput - formatted output character stream
     */
    private PrintWriter clientsOutput;
    
    /**
     * 
     * @param socket - socket which represents connection to the client
     * @throws IOException when creating input or output stream exception occurs
     * Constructor of SingleServiceConnection class which assigns input and output streams 
     * to proper class fields
     */
    public SingleServiceConnection(Socket socket) throws IOException {
        
        this.socket = socket;
        this.clientsOutput = new PrintWriter(
            new BufferedWriter(
            new OutputStreamWriter(
            this.socket.getOutputStream())), true);
        
        this.clientsInput = new BufferedReader(
            new InputStreamReader(
            this.socket.getInputStream()));
    }
    
    /**
     * 
     * @return message sent by client as String
     * @throws IOException when exception occurs when getting input from client
     * Gets input sent by client
     */
    public String getClientsInput() throws IOException {
        
        String clientsMessage = null;
        try {
            clientsMessage = this.clientsInput.readLine();            
        } catch(IOException e) {
            throw e;
        }
        
        return clientsMessage;
    }
    
    /**
     * 
     * @param message to send to the client as String
     * Sends message to the client
     */
    public void sendMessageToClient(String message) {
        
        this.clientsOutput.println(message);           
    }
    
    /**
     * 
     * @throws IOException when exception occurs when closing socket
     * Closes the socket
     */
    @Override
    public void close() throws IOException {
        
        if(this.socket != null) {
            this.socket.close();
        }        
    }    
}
