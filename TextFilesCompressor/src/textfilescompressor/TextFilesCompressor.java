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
     * @param -c option in command line to choose compress file mode
     * @param -d option in command line to choose decompress file mode
     * @param -i specifies input file name
     * @param -o specifies output file name
     */
    public static void main(String[] args) {
        
       Controller controller = new Controller();
       controller.startProgram(args);       
    }
    
}
