package textfilescompressor.view;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Piotr Matras
 * @version 1.0.1
 */
public class View {  
    /**
     * ArgsCount - constant variable which defines default number of args to get from user
     * (if not passed as CMD args)
     */
    private final int ArgsCount = 3;
    /**
     * scanner - Scanner object to get input from user
     */
    private final Scanner scanner;
    
    public View() {
        
        this.scanner = new Scanner(System.in);
    }
    
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
    /**
     * 
     * @return array of Strings which stores arguments from user 
     * Method gets arguments from user, if arguments weren't specified as CMD args
     */
    public String[] getArgsFromUser() {
        
        View view = new View();        
        String[] args = new String[ArgsCount];
        
        view.displayMessage("Please enter input file name with its extension:");
        String usersInFileName = scanner.next();
        view.displayMessage("Please enter output file name with its extension:");
        String usersOutFileName = scanner.next();
        view.displayMessage("Please enter mode of program\n1.compress,\n2.decompress.\nYour choice: ");
        int usersMode = scanner.nextInt();
        
        args[0] = usersInFileName;
        args[1] = usersOutFileName;
        switch(usersMode) {
            case 1: {
                
                args[2] = Mode.compress.toString();
                break;
            }
            case 2: {
                
                args[2] = Mode.decompress.toString();
            }
        }
        
        return args;
    }
    
     public enum Mode {
        
        compress,
        decompress 
    }    
}
