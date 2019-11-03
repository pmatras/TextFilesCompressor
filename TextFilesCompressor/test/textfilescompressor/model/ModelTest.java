package textfilescompressor.model;

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
    
    private static String fileToCompressName;
    private static String compressedOutFileName;
    private static String fileToDecompressName;
    private static String decompressedOutFileName;
    
    private Compressor compressor;
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
    
}
