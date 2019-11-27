package textfilescompressorserver.server;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class Server implements Closeable {
    
    /**
     * propertiesFileName - const which specify .properties file name, when server properties are stored
     */
    private final String propertiesFileName = ".properties";
    /**
     * serverPort - port on which server is working
     */
    private int serverPort;
    /**
     * defaultServerPort - default port number on which server is working - it is used if port is not specified in .properties file
     */
    private final int defaultServerPort = 8888;
    
    /**
     * serverSocket - represents the socket waiting for client connection
     */    
    private ServerSocket serverSocket;
    
    /**
     * Call setServerProperties method which parse server properties from .properties file
     */
    public Server() {
        
        setServerProperties();
    }
    
    /**
     * Starts server - creates the ServerSocket starting from port specified in .properties file
     * (or default port specified in field defaultServerPort) until it will create ServerSocket
     * on port which is not already binded
     */
    public void startServer() {
             
        int port = this.serverPort;
        boolean binded = false;
        
        while(!binded) {
            try {
                this.serverSocket = new ServerSocket(port);  
                this.serverPort = port;
                binded = true;
            } catch(IOException e) {
                System.err.println("Failed to start server on port " + port + "\nReason: " + e.getMessage());
                System.out.println("\nNow trying to start server on different port...");
                ++port;
            }
        } 
        
        System.out.println("\nServer started successfuly on port " + port + " On address: " + this.serverSocket.getInetAddress());
    }
    
    /**
     * Sets server properties which are stored in .properties file
     * or use defaultServerPort if server port is not specified in .properties
     */
    private void setServerProperties() {
        
        Properties properties = new Properties(); 
        
        try(FileInputStream propertiesFile = new FileInputStream(propertiesFileName)) {
            properties.load(propertiesFile);
            this.serverPort = Integer.parseInt(properties.getProperty("port"));            
        } catch(IOException e) {
            System.err.println(e.getMessage() + "\nNo server port specified in .properties file!\nServer will work on default port number = " + this.defaultServerPort );
            this.serverPort = this.defaultServerPort;           
        }
    }
    
    /**
     * 
     * @return returns ServerSocket class instance
     * Getter for field serverSocket
     */
    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }
    
    /**
     * 
     * @throws IOException if closing of serverSocket was unsuccessfull
     * Closes the ServerSocket
     */
    @Override
    public void close() throws IOException {
        if(this.serverSocket != null) {
            this.serverSocket.close();
        }        
    }    
}
