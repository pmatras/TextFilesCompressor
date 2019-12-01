package textfilescompressorclient;

import textfilescompressorclient.controller.ClientServerFlowGuard;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class TextFilesCompressorClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClientServerFlowGuard controller = new ClientServerFlowGuard();
        controller.runClient();
    }    
}
