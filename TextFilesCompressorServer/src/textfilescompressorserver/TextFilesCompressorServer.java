package textfilescompressorserver;

import textfilescompressorserver.servercontroller.ServerController;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class TextFilesCompressorServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        
        while(true) {
            ServerController controller = new ServerController();
            controller.run();
        }    
    }
}
