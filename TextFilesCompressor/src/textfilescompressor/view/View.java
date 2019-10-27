package textfilescompressor.view;

import java.util.Scanner;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class View {    
    
    public void displayWelcomeScreen() {
        
        System.out.println("Welcome in text files compressor application.");
    }
    
    public void clearScreen() {
        
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
    /**
     * 
     * @param message message which will be displayed on screen 
     * Display message passed as a argument on screen
     */
    public void displayMessage(String message) {
        
        System.out.println(message);
    }
    
}
