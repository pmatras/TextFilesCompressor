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
    
    private Socket socket;
    private BufferedReader clientsInput;
    private PrintWriter clientsOutput;
    
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

    @Override
    public void close() throws IOException {
        
        if(this.socket != null) {
            this.socket.close();
        }        
    }    
}
