package textfilescompressorclient.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class ClientsSocket {
    /**
     * socket - represents connection to the server
     */
    private Socket socket;
    /**
     * outputForServer - formatted output character stream
     */
    private PrintWriter outputForServer;
    /**
     * outputFromServer - buffered input character stream
     */
    private BufferedReader outputFromServer;
    
    /**
     * propertiesFileName - const which specify .properties file name, when server properties are stored
     */
    private final String propertiesFileName = ".properties";
    /**
     * serverHost - name of host on which server runs
     */
    private String serverHost;
    /**
     * defaultServerHost - name of host on which server runs to use when hostname isn't specified
     * in .properties file
     */
    private final String defaultServerHost = "localhost";
    /***
     * serverPort - port number on which server is listening
     */
    private int serverPort = 0;
    /**
     * defaultServerPort - port number on which server is listening to use when port number
     * isn't specified in .properties file
     */
    private final int defaultServerPort = 8888;
    
    /**
     * default constructor of class - invokes method which sets server properties from
     * .properties file, creates socket and assigns input and output streams 
     */
    public ClientsSocket() {
        
        setServerProperties(); 
        
        try {
            this.socket = new Socket(serverHost, serverPort);
            this.outputFromServer = new BufferedReader(
                    new InputStreamReader(
                            this.socket.getInputStream()));
            this.outputForServer = new PrintWriter( 
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);                    
        } catch(IOException e) {
            System.err.println("Unable to initialize connection with server, reason: " + e.getMessage());            
        }        
    }
    
    /**
     * sets server properties from .properties file, in case of not specified
     * proper properties in .properties file, mathod sets default server properties
     */
    private void setServerProperties() {
        
        Properties properties = new Properties(); 
        
        try(FileInputStream propertiesFile = new FileInputStream(this.propertiesFileName)) {
            properties.load(propertiesFile);
            this.serverPort = Integer.parseInt(properties.getProperty("port"));
            this.serverHost = properties.getProperty("host");
        } catch(IOException e) {
            System.err.println(e.getMessage() + "\nNo server port specified in .properties file!\nClient will try to connect to server on default port number = " + this.defaultServerPort );
            if(this.serverPort == 0) {
                this.serverPort = this.defaultServerPort;                
            } else if(this.serverHost == null) {
                this.serverHost = defaultServerHost;
            }               
        }
    }
    
    /**
     * 
     * @param message - String message which will be send to the server
     * Sends message to the server
     */
    public void sendMessageToServer(String message) {
        
        this.outputForServer.println(message);      
    }
    
    /**
     * 
     * @return String message from server
     * Reads input from server
     */
    public String readMessageFromServer() {
        
        String messageFromServer = "";
        try {
             messageFromServer =  this.outputFromServer.readLine();
        } catch(IOException e) {
            System.err.println("Unable to read message from server, reason: " + e.getMessage());
        }
       return messageFromServer;
    }
    
    /**
     * 
     * @return int port number
     * Getter for field serverPort
     */
    public int getServerPort() {
        return this.serverPort;
    }
    
    /**
     * 
     * @return String server host
     * Getter for field serverHost
     */
    public String getServerHost() {
        return this.serverHost;
    }
    
    /**
     * 
     * @throws IOException when closing socket threw exception
     * Closes socket
     */
    public void closeSocket() throws IOException {
        
        if(this.socket != null) {
            this.socket.close();
        }
    }
}
