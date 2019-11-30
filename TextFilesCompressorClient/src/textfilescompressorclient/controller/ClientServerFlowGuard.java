/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfilescompressorclient.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import textfilescompressorclient.view.ClientsInterface;

/**
 *
 * @author Piotr Matras 
 * @version 1.0
 */
public class ClientServerFlowGuard {
    
    private final Scanner scanner;
    private String inFileName;
    private String outFileName;
    private String mode;
    
    public ClientServerFlowGuard() {
        
        this.scanner = new Scanner(System.in);
    }
    
    public void runClient() {
        
        ClientsInterface view = new ClientsInterface();
    }
    
    private void getArgsFromUser(final ClientsInterface view) {
        
        view.printMessageForClient("Please enter input file name with its extension:");
        this.inFileName = this.scanner.next();
        view.printMessageForClient("Please enter output file name with its extension:");
        this.outFileName = this.scanner.next();
        view.printMessageForClient("Please enter mode of program\n1.compress,\n2.decompress.\nYour choice: ");
        
         int usersMode = 0;
        try {
            
            usersMode = scanner.nextInt();            
        } catch(InputMismatchException e) {
            
            view.printMessageForClient("Wrong choice. Default mode - compress will be used.");
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
