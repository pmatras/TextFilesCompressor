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
import textfilescompressorserver.servermodel.WrongFilePassedException;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class ServerController {
    /**
     * server - object of Server class representing working server
     */
    private Server server = null;
    /**
     * socket - object which represents connection to the client
     */
    private Socket socket = null;
    /**
     * service - object of SingleServiceConnection class representing single connection to the client
     */
    private SingleServiceConnection service = null;
    
    /**
     * mode - mode of filesCompressor (compression or decompression)
     */    
    private String mode;
    
    /**
     * constructor of ServerController class
     */
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
    
    /**
     * Main method of ServerController - gets arguments from user to start compressing/decompressing file,
     * parses them and process client's request - start compressing or decompressing file specified by client
     */
    public void run() {
        
        String clientsInput = "";
        List<String> args = new ArrayList<String>();
       
        this.service.sendMessageToClient("Welcome to Java TextFilesCompressor Server! To get HELP please type 'help'");
        
        do {
            this.service.sendMessageToClient("Please type arguments:\n");
            args.clear();
            do{
                try{
                    clientsInput = this.service.getClientsInput();
                    if(!clientsInput.toLowerCase().equals("start") && !clientsInput.toLowerCase().equals("help")) {
                        args.add(clientsInput);
                        this.service.sendMessageToClient("Status code " + Status.ACCEPTED.ordinal() + " - " + Status.ACCEPTED.toString() + "\n");
                    } else if(clientsInput.toLowerCase().equals("start")) {
                        this.service.sendMessageToClient("\nStarting processing your request...\n"); 
                    } else {
                        sendHelpToClient();
                    }    
                } catch(IOException e) {
                    System.err.println("Exception occured while getting input from client - reason: " + e.getMessage());
                }
            } while(!clientsInput.toLowerCase().equals("start"));
            
            Map<String, String> parsedArgs = parseArguments(args);
            processClientsRequest(parsedArgs); 
            
            this.service.sendMessageToClient("Type 'quit' to exit or press enter to continue...\n");
            try{
                clientsInput = this.service.getClientsInput();
            } catch(IOException e) {
                System.err.println("Exception occured while getting input from client - reason: " + e.getMessage());
            }
        } while(!clientsInput.toLowerCase().equals("quit"));        
       
        try { 
            this.service.close();
            System.out.println("Socket closed.");        
        } catch(IOException e) {
            System.err.println("Failed to close service, reason: " + e.getMessage());
        }
        
        try { 
            this.server.close();
            System.out.println("Server closed.");        
        } catch(IOException e) {
            System.err.println("Failed to close server, reason: " + e.getMessage());
        }        
    }
    
    /**
     * 
     * @param args - map which contains arguments from user mapped to its meanings to the program
     * Method call compress or decompress method from object of class ServerFilesCompressor,
     * depending on arguments passed by client and sends to client proper messages
     */
    private void processClientsRequest(Map<String, String> args) {
        
        this.mode = (args.get("mode") != null) ? args.get("mode") : "";
        ServerFilesCompressor filesCompressor = null;
        
        if(this.mode.equals(Mode.COMPRESS.toString().toLowerCase())) {            
            filesCompressor = SingleInstanceOfServerFileCompressorGuard.getFilesCompressor(args.get("inputFileName"), "", args.get("outputFileName"));
            
            try {
                if(filesCompressor.compressFile()) {                    
                    this.service.sendMessageToClient("File " + args.get("inputFileName") + " compressed succesfully into file " + args.get("outputFileName") + "\n");
                }
            } catch(WrongFilePassedException e) {                
                this.service.sendMessageToClient("File to compress not found: " + e.getMessage());
            } catch(IOException e) {                
                this.service.sendMessageToClient("Problem occured while compressing file: " + e.getMessage());
            }            
        } else if(this.mode.equals(Mode.DECOMPRESS.toString().toLowerCase())) {            
            filesCompressor = SingleInstanceOfServerFileCompressorGuard.getFilesCompressor("", args.get("inputFileName"), args.get("outputFileName"));            
            try {
                if(filesCompressor.decompressFile()) {                    
                    this.service.sendMessageToClient("File " + args.get("inputFileName") + " decompressed successfully into file " + args.get("outputFileName") + "\n");
                }
            } catch(WrongFilePassedException e) {                    
                    this.service.sendMessageToClient("Problem while opening file to decompress. Can not finish: " + e.getMessage() + "\n");                 
            } catch(IOException e) {                
                this.service.sendMessageToClient("Problem while decompressing file. Can not finish: " + e.getMessage() + "\n");
            }
        } else {
            this.service.sendMessageToClient("Wrong arguments passed! Try again...\n");
        }  
    }
    
    /**
     * 
     * @param args - list, which contains all arguments passed by client
     * @return Map which contains client's arguments mapped to its meanings in program
     * Method parses arguments passed by client until client typed 'start' command
     */
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
    
    /**
     * Method sends help to client, it's called after client type 'help' command
     */
    private void sendHelpToClient() {
        
        this.service.sendMessageToClient("\nHelp for FilesCompressor server...\n\n");
        this.service.sendMessageToClient("-c : compress mode\n");
        this.service.sendMessageToClient("-d : decompress mode\n");
        this.service.sendMessageToClient("-i : input file, after this parameter please specify input file, which will be compressed/decompressed\n");
        this.service.sendMessageToClient("-o : output file, after this parameter please specify output file in which compressed/decompressed input file will be saved\n\n");
    }
    
    /**
     * Available modes of ServerFilesCompressor 
     */
    private enum Mode {
        
        COMPRESS,
        DECOMPRESS
    }
    
}
