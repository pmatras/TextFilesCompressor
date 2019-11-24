package textfilescompressorserver.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class SingleServiceConnection implements Closeable {
    
    private Socket socket;

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
