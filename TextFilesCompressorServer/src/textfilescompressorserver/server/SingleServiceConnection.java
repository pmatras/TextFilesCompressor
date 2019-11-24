package textfilescompressorserver.server;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
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

    @Override
    public void close() throws IOException {
        
        if(this.socket != null) {
            this.socket.close();
        }        
    }    
}
