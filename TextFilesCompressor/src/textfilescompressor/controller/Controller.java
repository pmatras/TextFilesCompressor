package textfilescompressor.controller;

import textfilescompressor.view.View;
import java.io.FileNotFoundException;
import java.io.IOException;
import textfilescompressor.model.Model;

/**
 *
 * @author Piotr Matras
 */
public class Controller {
    
    private String inFileName;
    private String outFileName;
    private String mode;
    
     public void startProgram(final String[] args) {
        
        setArguments(args);
        View view = new View();
        view.displayWelcomeScreen();
        
        Model model;
        if(this.mode.equals("compress")) {
            
            model =  new Model(inFileName, "", outFileName);
            
            try {
                if(model.compressFile()) {
                    
                    view.displayMessage("File " + inFileName + " compressed succesfully into file " + outFileName);
                }
            } catch(FileNotFoundException e) {
                
                view.displayMessage("File to compress not found: " + e.getMessage());
            } catch(IOException e) {
                
                view.displayMessage("Problem occured while compressing file: " + e.getMessage());
            }
            
        } else if(this.mode.equals("decompress")) {
            
            model = new Model("", inFileName, outFileName);
            
            try {
                if(model.decompressFile()) {
                    
                    view.displayMessage("File " + inFileName + " decompressed successfully into file " + outFileName);
                }
            } catch(FileNotFoundException e) {
                    
                    view.displayMessage("Problem while opening file to decompress. Can not finish: " + e.getMessage());
                 
            } catch(IOException e) {
                
                view.displayMessage("Problem while decompressing file. Can not finish: " + e.getMessage());
            }
            
    }
    }
      
    private void setArguments(String[] args) {
        
        if(args[0].equals("-c")) {
            
            this.mode = "compress";
        } else if(args[0].equals("-d")) {
            
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
