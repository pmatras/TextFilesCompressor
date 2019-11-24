package textfilescompressorserver.servercontroller;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class Server implements Closeable {
    
    private String inFileName;
    private String outFileName;
    private String mode;
    
    public void startServer() {
        
    }
    
    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
