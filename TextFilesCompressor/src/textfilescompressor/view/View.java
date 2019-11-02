package textfilescompressor.view;

/**
 *
 * @author Piotr Matras
 * @version 1.0.1
 */
public class View {  
    
    public void displayWelcomeScreen() {
        
        System.out.println("Welcome in text files compressor application.");
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
