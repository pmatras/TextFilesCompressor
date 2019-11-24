package textfilescompressorserver.servercontroller;

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
    
    private String inFileName;
    private String outFileName;
    private String mode;
    private final String propertiesFileName = ".properties";
    private int serverPort;
    private final int defaultServerPort = 8888;
    
    private ServerSocket serverSocket;
    
    public Server() throws IOException {
        setServerProperties();
        this.serverSocket = new ServerSocket(serverPort);       
    }
    
    public void startServer() {
        
    }
    
    public void setServerProperties() {
        
        Properties properties = new Properties(); 
        
        try(FileInputStream propertiesFile = new FileInputStream(propertiesFileName)) {
            properties.load(propertiesFile);
            this.serverPort = Integer.parseInt(properties.getProperty("port"));            
        } catch(IOException e) {
            System.err.println(e.getMessage() + "\nNo server port specified in .properties file!\nServer will work on default port number = " + this.defaultServerPort );
            this.serverPort = this.defaultServerPort;           
        }
    }
    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
