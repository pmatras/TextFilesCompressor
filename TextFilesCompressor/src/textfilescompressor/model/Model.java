package textfilescompressor.model;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class Model {
    /**
     * fileToCompressName - a file which will be compressed
     */
    private String fileToCompressName;
    /**
     * fileToDecompressName - a file which will be decompressed
     */
    private String fileToDecompressName;
    /**
     * outputFileName - a file when will be saved compressed or decompressed data
     */
    private String outputFileName;
    
    /**
     * 
     * @param fileToCompressName name of file which will be compressed (empty if user chose decompress file mode)
     * @param fileToDecompressName name of file which will be decompressed (empty if user chose compress file mode)
     * @param outputFileName name of file which will store compressed or decompressed input file chosen by user
     */
    public Model(final String fileToCompressName, final String fileToDecompressName, final String outputFileName) {
        
        this.fileToCompressName = fileToCompressName;
        this.fileToDecompressName = fileToDecompressName;
        this.outputFileName = outputFileName;
    }
    
    /**
     * 
     * @return true if compression of file was successfull
     * @throws WrongFilePassedException if FileNotFoundException was catched, method will throw this
     * @throws IOException throws if during saving compressed data to file exception will occur
     * Method for compressing file passed as cmd argument
     */
    public boolean compressFile() throws WrongFilePassedException, IOException {
        
        FileInputStream inputFile = null;
        FileOutputStream outputFile = null;
        try {
            
            inputFile = new FileInputStream(this.fileToCompressName); 
            outputFile = new FileOutputStream(this.outputFileName); 
        } catch(FileNotFoundException e) {
            
            throw new WrongFilePassedException(e.getMessage());
        }
 
        DeflaterOutputStream compressedFile = new DeflaterOutputStream(outputFile); 
        
        int data; 
        
        try {
            while ((data = inputFile.read())!= -1) { 
                compressedFile.write(data); 
            } 
        } catch(IOException e) {
            
            throw e;
        }  
     
        inputFile.close(); 
        compressedFile.close(); 
        
        return true;
    }
    
    /**
     * 
     * @return true if decompression of file was successfull
     * @throws WrongFilePassedException if FileNotFoundException was catched during opening files, method will throw this 
     * @throws IOException throws if during saving decompressed data to file exception will occur
     * Method for decompressing file passed as a cmd argument
     */
    public boolean decompressFile() throws WrongFilePassedException, IOException {
        
        FileInputStream inputFile = null;
        FileOutputStream outputFile = null;
        try {
            inputFile = new FileInputStream(this.fileToDecompressName);
            outputFile = new FileOutputStream(this.outputFileName); 
        } catch(FileNotFoundException e) {
            
            throw new WrongFilePassedException(e.getMessage());
        }
  
        InflaterInputStream decompressedFile = new InflaterInputStream(inputFile); 
        
        int data; 
        
        try { 
            
            while((data = decompressedFile.read())!= -1) { 
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
