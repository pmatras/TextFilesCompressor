package textfilescompressor.view;

import java.util.Scanner;

/**
 *
 * @author Piotr Matras
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
    
    public void displayMessage(String message) {
        
        System.out.println(message);
    }
    
}
