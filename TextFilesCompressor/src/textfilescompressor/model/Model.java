package textfilescompressor.model;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.zip.Deflater; 
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author Piotr Matras
 */
public class Model {
    
    private String fileToCompressName;
    private String fileToDecompressName;
    private String outputFileName;
    
    public Model(final String fileToCompressName, final String fileToDecompressName, final String outputFileName) {
        
        this.fileToCompressName = fileToCompressName;
        this.fileToDecompressName = fileToDecompressName;
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
    
    public boolean decompressFile() throws FileNotFoundException, IOException {
        
        FileInputStream inputFile = null;
        FileOutputStream outputFile = null;
        try {
            inputFile = new FileInputStream(this.fileToDecompressName);
            outputFile = new FileOutputStream(this.outputFileName); 
        } catch(FileNotFoundException e) {
            
            throw e;
        }
  
        InflaterInputStream decompressedFile =new InflaterInputStream(inputFile); 
        
        int data; 
        
        try { 
            
            while((data = decompressedFile.read())!=-1) { 
                outputFile.write(data); 
            }      
        } catch(IOException e) {
            
            throw e;
        }
        
        outputFile.close(); 
        decompressedFile.close(); 
        
        return true;
    }
    
}
