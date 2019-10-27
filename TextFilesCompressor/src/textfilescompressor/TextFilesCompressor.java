package textfilescompressor;

import textfilescompressor.controller.Controller;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class TextFilesCompressor {

    /**
     * @param args the command line arguments
     * <br>Order of cmd arguments:
     * <br>-c option in command line to choose compress file mode
     * <br>-d option in command line to choose decompress file mode
     * <br>-i specifies input file name
     * <br>-o specifies output file name
     */
    public static void main(String[] args) {
        
       Controller controller = new Controller();
       controller.startProgram(args);       
    }
    
}
