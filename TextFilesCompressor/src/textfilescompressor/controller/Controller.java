package textfilescompressor.controller;

import textfilescompressor.view.View;

/**
 *
 * @author Piotr Matras
 */
public class Controller {
    
    private String inFileName;
    private String outFileName;
    private String mode;
      
    private void setArguments(String[] args) {
        
        if(args[0].equals("-c")) {
            
            this.mode = "compress";
        } else if(args[1].equals("-d")) {
            
            this.mode = "decompress";
        }
        
        if(args[1].equals("-i")) {
            
            this.inFileName = args[2];
        } 
        if(args[3].equals("-o")) {
            
            this.outFileName = args[4];
        }
        
    }
    
}
