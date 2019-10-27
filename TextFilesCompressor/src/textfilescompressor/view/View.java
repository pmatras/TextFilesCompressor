package textfilescompressor.view;

import java.util.Scanner;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class View {    
     
    private final Scanner scanner;
    
    public View() {
        
        this.scanner = new Scanner(System.in);
    }
    
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
     */
    public void displayMessage(String message) {
        
        System.out.println(message);
    }
    
}
