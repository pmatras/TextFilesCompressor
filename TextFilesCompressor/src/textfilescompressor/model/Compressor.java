package textfilescompressor.model;

import java.io.IOException;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public interface Compressor {
    
    public boolean compressFile() throws WrongFilePassedException, IOException;
    public boolean decompressFile() throws WrongFilePassedException, IOException;    
}
