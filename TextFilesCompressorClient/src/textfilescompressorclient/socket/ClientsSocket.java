package textfilescompressorclient.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class ClientsSocket {
    
    private Socket socket;
    private DataOutputStream outputForServer;
    private BufferedReader outputFromServer;
    
    private final String propertiesFileName = ".properties";
    private String serverHost;
    private final String defaultServerHost = "localhost";
    private int serverPort = 0;
    private final int defaultServerPort = 8888;
    
    public ClientsSocket() {
        
        setServerProperties(); 
        
        try {
            this.socket = new Socket(serverHost, serverPort);
            this.outputFromServer = new BufferedReader(
                new InputStreamReader(
                this.socket.getInputStream()));   
            this.outputForServer = new DataOutputStream(
                this.socket.getOutputStream());
        } catch(IOException e) {
            System.err.println("Unable to initialize connection with server, reason: " + e.getMessage());            
        }
        
    }
    
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
    
    public int getServerPort() {
        return this.serverPort;
    }
    
    public String getServerHost() {
        return this.serverHost;
    }
}
