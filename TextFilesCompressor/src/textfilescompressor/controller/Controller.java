package textfilescompressor.controller;

import textfilescompressor.view.View;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import textfilescompressor.model.Model;
import textfilescompressor.model.WrongFilePassedException;

/**
 *
 * @author Piotr Matras
 * @version 1.0.1
 */
public class Controller {
    /**
     * inFileName - file which will be compressed or decompressed
     */
    private String inFileName;
    /**
     * outFileName - file when compressed or decompressed data will be saved
     */
    private String outFileName;
    /**
     * mode - mode of program (compressing or decompressing)
     */
    private String mode;
    /**
     * ArgsCount - constant variable which defines default number of args to get from user
     * (if not passed as CMD args)
     */
    private final int ArgsCount = 3;
     /**
     * scanner - Scanner object to get input from user
     */
    private final Scanner scanner = new Scanner(System.in);
    
    /**
     * 
     * @param args command line arguments
     * Main method of whole application - handles users choice of working mode and process everything
     */
     public void startProgram(String[] args) {
         
        View view = new View();
        
        try {
            
            setArguments(args);            
        } catch(ArrayIndexOutOfBoundsException e) {
            
            view.displayMessage("Wrong run arguments passed!");
            getArgsFromUser(view);
        }
        
        view.displayWelcomeScreen();
        
        Model model;
        if(this.mode.equals("compress")) {
            
            model =  new Model(inFileName, "", outFileName);
            
            try {
                if(model.compressFile()) {
                    
                    view.displayMessage("File " + inFileName + " compressed succesfully into file " + outFileName);
                }
            } catch(WrongFilePassedException e) {
                
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
            } catch(WrongFilePassedException e) {
                    
                    view.displayMessage("Problem while opening file to decompress. Can not finish: " + e.getMessage());
                 
            } catch(IOException e) {
                
                view.displayMessage("Problem while decompressing file. Can not finish: " + e.getMessage());
            }
            
    }
    }
      
     /**
      *
      * @param args command line arguments
      * Parses the users command line arguments
      */
    private void setArguments(String[] args) {
        
        try {
            
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
        } catch(ArrayIndexOutOfBoundsException e) {
            
            throw e;           
        }        
    }        
    /**
     * 
     * @param view object of View class to display prompts to user
     * Method gets arguments from user, if arguments weren't specified as CMD args
     */
    public void getArgsFromUser(final View view) {
          
        view.displayMessage("Please enter input file name with its extension:");
        this.inFileName = scanner.next();
        view.displayMessage("Please enter output file name with its extension:");
        this.outFileName = scanner.next();
        view.displayMessage("Please enter mode of program\n1.compress,\n2.decompress.\nYour choice: ");
        
        int usersMode = 0;
        try {
            
            usersMode = scanner.nextInt();            
        } catch(InputMismatchException e) {
            
            view.displayMessage("Wrong choice. Default mode - compress will be used.");
            usersMode = 1;
        }
        
        switch(usersMode) {
            case 1: {
                
                this.mode = Mode.compress.toString();
                break;
            }
            case 2: {
                
               this.mode = Mode.decompress.toString();
            }
        }
    }
    
     private enum Mode {
        
        compress,
        decompress 
    }    
}
