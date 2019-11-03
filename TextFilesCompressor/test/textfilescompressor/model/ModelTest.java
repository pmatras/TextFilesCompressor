package textfilescompressor.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 * Tests for all public methods in Model class
 */
public class ModelTest {
    /**
     * fileToCompressName - name of file which will be compressed
     */
    private static String fileToCompressName;
    /**
     * compressedOutFileName - name of file to save compressed content
     */
    private static String compressedOutFileName;
    /**
     * fileToDecompressName - name of file which will be decompressed
     */
    private static String fileToDecompressName;
    /**
     * decompressedOutFileName - name of file to save decompressed content
     */
    private static String decompressedOutFileName;
    /**
     * compressor - object of Model class to compress file
     */
    private Compressor compressor;
    /**
     * decompressor - object of Model class to decompress file
     */
    private Compressor decompressor;
    
    public ModelTest() {
        
        ModelTest.fileToCompressName = "test.txt";
        ModelTest.compressedOutFileName = "out.txt";
        ModelTest.fileToDecompressName = "out.txt";
        ModelTest.decompressedOutFileName = "decompressed.txt";
    }
    
    @Before
    public void setUp() {
        
        compressor = new Model(fileToCompressName, "", compressedOutFileName);
        decompressor = new Model("", fileToDecompressName, decompressedOutFileName);  
    }

    /**
     * Test of compressFile method, of class Model.
     * Tests if compression of example file is succesfully
     */
    @Test
    public void testCompressFile() throws Exception {
       
        boolean expResult = true;
        boolean result = compressor.compressFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of decompressFile method, of class Model.
     * Tests if decompression of example file is succesfully
     */
    @Test
    public void testDecompressFile() throws Exception {
       
        boolean expResult = true;
        boolean result = decompressor.decompressFile();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of compressFile method, of class Model
     * Tests if method handles passing to method non-existent file
     * @throws java.io.IOException - thrown when error occured while reading/writing to file, mustn't occur here
     */
    @Test
    public void testCompressFileWithNonExistentFile() throws IOException {
        
        Compressor compressor = new Model("nonexistentfile.txt", "", compressedOutFileName);
        
        try {
            
            compressor.compressFile();  
            fail("Exception should be thrown when trying to compress un-existent file");
        } catch(WrongFilePassedException e) {
            
        }     
    }
    
     /**
     * Test of decompressFile method, of class Model
     * Tests if method handles passing to method non-existent file
     * @throws java.io.IOException - thrown when error occured while reading/writing to file, mustn't occur here
     */
    @Test
    public void testDecompressFileWithNonExistentFile() throws IOException {
        
        Compressor decompressor = new Model("", "nonexistentfile.txt", decompressedOutFileName);
        
        try {
            
            decompressor.decompressFile();  
            fail("Exception should be thrown when trying to decompress un-existent file");
        } catch(WrongFilePassedException e) {
            
        }     
    }
    /**
     * Test of compressFile method, of class Model
     * Tests if method handles passing to method null file name
     * @throws java.io.IOException - thrown when error occured while reading/writing to file, mustn't occur here
     */ 
    @Test
    public void testCompressFileWithNullFileName() throws IOException {
        
        Compressor compressor = new Model(null, "", compressedOutFileName);
        
        try {
            
            compressor.compressFile();  
            fail("Exception should be thrown when trying to compress null file");
        } catch(WrongFilePassedException e) {
            
        }            
    }
    
     /**
     * Test of decompressFile method, of class Model
     * Tests if method handles passing to method non-existent file
     * @throws java.io.IOException - thrown when error occured while reading/writing to file, mustn't occur here
     */
    @Test
    public void testDecompressFileWithNullFileName() throws IOException {
        
        Compressor decompressor = new Model("", null, decompressedOutFileName);
        
        try {
            
            decompressor.compressFile();  
            fail("Exception should be thrown when trying to decompress null file");
        } catch(WrongFilePassedException e) {
            
        }            
    }
    /**
     * This is not pure unit test - Test both compressFile and decompressFile methods, of class Model
     * Tests if decompression of file which was compressed by compressFile method by decompressFile method gets original file
     * @throws java.io.IOException - thrown when error occured while reading/writing to file
     */
    @Test
    public void testIfDecompressedCompressedFileIsTheSameAsOriginal() throws IOException {
        
        try {
            
            boolean compressedSuccessfully = compressor.compressFile();
            boolean decompressedSuccessfully = decompressor.decompressFile();            
        } catch(WrongFilePassedException e) {
            
            fail("Failed to compress and then decompress this file");
        }
        
        byte[] originalFile = Files.readAllBytes(Paths.get(fileToCompressName));
        byte[] decompressedFile = Files.readAllBytes(Paths.get(decompressedOutFileName));
        
        boolean expResult = true;
        assertEquals(expResult, Arrays.equals(originalFile, decompressedFile));        
    }    
}
