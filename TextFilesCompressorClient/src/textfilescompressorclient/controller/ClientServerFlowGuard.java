package textfilescompressorclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import textfilescompressorclient.socket.ClientsSocket;
import textfilescompressorclient.view.ClientsInterface;

/**
 *
 * @author Piotr Matras 
 * @version 1.0
 */
public class ClientServerFlowGuard {
    
    /**
     * scanner - instance of class Scanner to get input from client
     */
    private final Scanner scanner;
    /**
     * inFileName - name of input file, which will be used for textFilesCompressor
     */
    private String inFileName;
    /**
     * outFileName - name of output file, which will be used for textFilesCompressor
     */
    private String outFileName;
    /**
     * mode - mode of textFilesCompressor(compressing or decompressing)
     */
    private String mode;
    
    /**
     * Default constructor - assigns new instance of Scanner class to scanner field
     */
    public ClientServerFlowGuard() {
        
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method of client's program - creates new instances of ClientsScoket and ClientsInterface classes
     * Handles getting from client arguments for server and sends it to the server, also reads output from server
     */
    public void runClient() {
        
        ClientsSocket socket = new ClientsSocket();       
        ClientsInterface view = new ClientsInterface(); 
        view.printMessageForClient("Connected to server on address: " + socket.getServerHost() + ":" + socket.getServerPort());
        boolean closeSocket = false;
        List<String> messagesForServer = new ArrayList<>();
        
        view.printMessageForClient(socket.readMessageFromServer());
        
        do {
            getArgsFromUser(view);
            messagesForServer.clear();
            if(this.mode.equals(Mode.compress.toString())) {
                 messagesForServer.add("-c");                 
            } else {
            messagesForServer.add("-d");
            }
            messagesForServer.add("-i");
            messagesForServer.add(this.inFileName);
            messagesForServer.add("-o");
            messagesForServer.add(this.outFileName);
            messagesForServer.add("start");
            
            view.printMessageForClient("Sending your request to server...\nServer will be responding with status codes."); 
            socket.readMessageFromServer();
            
            for(String arg : messagesForServer) {
                socket.sendMessageToServer(arg);
                view.printMessageForClient(socket.readMessageFromServer());
            }
            
            view.printMessageForClient(socket.readMessageFromServer());
            socket.readMessageFromServer();
            view.printMessageForClient("Please type 'quit' to exit or type anything to continue...");
            
            if(this.scanner.next().toLowerCase().equals("quit")) {
                closeSocket = true;
                socket.sendMessageToServer("quit");
            } else {
                socket.sendMessageToServer("continue");
            }
            
        } while(closeSocket == false);
        
        try {
            socket.closeSocket();
            System.out.println("Connection with server closed.");
        } catch(IOException e) {
            System.err.println("Unable to close connection with server, reason: " + e.getMessage());
        }        
    }
    
    /**
     * 
     * @param view - instance of ClientsInterface class
     * Handles getting from client arguments for the server
     */
    private void getArgsFromUser(final ClientsInterface view) {
        
        view.printMessageForClient("Please enter input file name with its extension:");
        this.inFileName = this.scanner.next();
        view.printMessageForClient("Please enter output file name with its extension:");
        this.outFileName = this.scanner.next();
        view.printMessageForClient("Please enter mode of program\n1.compress,\n2.decompress.\nYour choice: ");
        
         int usersMode = 0;
        try {
            
            usersMode = scanner.nextInt();            
        } catch(InputMismatchException e) {
            
            view.printMessageForClient("Wrong choice. Default mode - compress will be used.");
            usersMode = 1;
        }
        
        switch(usersMode) {
            case 1: {
                
                this.mode = Mode.compress.toString();
                break;
            }
            case 2: {
                
               this.mode = Mode.decompress.toString();
            }
        }         
    }
    
    /**
     * Mode - enum class with possible working modes of textFilesCompressor
     */
     private enum Mode {
        
        compress,
        decompress 
    }    
    
}
