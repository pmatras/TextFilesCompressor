package textfilescompressorserver.servercontroller;

import java.io.IOException;
import textfilescompressorserver.server.Server;
import textfilescompressorserver.server.SingleServiceConnection;
import java.net.Socket;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class ServerController {
    
    private Server server = null;
    private Socket socket = null;
    private SingleServiceConnection service = null;
    
    public ServerController() {
        
        this.server = new Server();   
        this.server.startServer();
        try {
            this.socket = this.server.getServerSocket().accept();
            this.service = new SingleServiceConnection(socket);
        } catch(IOException e) {
            System.err.println("Failed to initialize ServerController. Reason: " + e.getMessage());             
        }
    }
    
}
