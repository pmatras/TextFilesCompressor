package textfilescompressor;

import textfilescompressor.controller.Controller;

/**
 *
 * @author Piotr Matras
 */
public class TextFilesCompressor {

    /**
     * @param args the command line arguments
     * -c / -d : choose mode - -c means compress, -d means decompress
     * -i : after this arg please specify input file name
     * -o : after this arg please specify outpu file name
     */
    public static void main(String[] args) {
        
       Controller controller = new Controller();
       controller.startProgram(args);       
    }
    
}
