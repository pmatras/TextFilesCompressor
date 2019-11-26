package textfilescompressorserver.servercontroller;

import java.io.IOException;
import textfilescompressorserver.server.Server;
import textfilescompressorserver.server.SingleServiceConnection;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import textfilescompressorserver.servermodel.ServerFilesCompressor;
import textfilescompressorserver.servermodel.SingleInstanceOfServerFileCompressorGuard;

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
    
    public void run() {
        
        String clientsInput = "";
        List<String> args = new ArrayList<String>();
       
        this.service.sendMessageToClient("Welcome to Java TextFilesCompressor Server!");
        
        do{
            try{
                clientsInput = this.service.getClientsInput();
                if(!clientsInput.toLowerCase().equals("start")) {
                    args.add(clientsInput);
                    this.service.sendMessageToClient("Status code " + Status.ACCEPTED.ordinal() + " - " + Status.ACCEPTED.toString());
                } else {
                    this.service.sendMessageToClient("Starting processing your request...");
                }                 
            } catch(IOException e) {
                System.err.println("Exception occured while getting input from client - reason: " + e.getMessage());
            }
            
        } while(!clientsInput.toLowerCase().equals("start"));
        
        try { 
            this.service.close();
            System.out.println("Socket closed.");        
        } catch(IOException e) {
            System.err.println("Failed to close service, reason: " + e.getMessage());
        }
        
    }
    
    private Map<String, String> parseArguments(List<String> args) {
        
        Map<String, String> arguments = new HashMap<String, String>();
        
        String previousParam = "";
        
        for(String param : args) {
            
            if(param.equals("-c")) {
                arguments.put("mode", "compress");
                continue;
            }
            if(param.equals("-d")) {
                arguments.put("mode", "decompress");
                continue;
            }
            if(previousParam.equals("-i") && !param.equals("-o")) {
                arguments.put("inputFileName", param);
                previousParam = "";
                continue;
            }
            if(previousParam.equals("-o") && !param.equals("-i")) {
                arguments.put("outputFileName", param);
                previousParam = "";
                continue;
            }                
                previousParam = param;                                       
        }        
        
       return arguments;
    }
    
    private enum Mode {
        
        COMPRESS,
        DECOMPRESS
    }
    
}
