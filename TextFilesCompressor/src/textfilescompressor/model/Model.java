package textfilescompressor.model;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.zip.Deflater; 
import java.util.zip.DeflaterOutputStream;

/**
 *
 * @author Piotr Matras
 */
public class Model {
    
    private String fileToCompressName;
    private String fileToDecompressName;
    private String outputFileName;
    
    public Model(final String inFileName, final String outFileName, final String outputFileName) {
        
        this.fileToCompressName = inFileName;
        this.fileToDecompressName = outFileName;
        this.outputFileName = outputFileName;
    }
    
    public boolean compressFile() throws FileNotFoundException, IOException {
        
        FileInputStream inputFile = null;
        FileOutputStream outputFile = null;
        try {
            
            inputFile = new FileInputStream(this.fileToCompressName); 
            outputFile = new FileOutputStream(this.outputFileName); 
        } catch(FileNotFoundException e) {
            
            throw e;
        }
 
        DeflaterOutputStream compressedFile = new DeflaterOutputStream(outputFile); 
        
        int data; 
        
        try {
            while ((data = inputFile.read())!=-1) { 
                compressedFile.write(data); 
            } 
        } catch(IOException e) {
            
            throw e;
        }  
     
        inputFile.close(); 
        compressedFile.close(); 
        
        return true;
    }
    
}
