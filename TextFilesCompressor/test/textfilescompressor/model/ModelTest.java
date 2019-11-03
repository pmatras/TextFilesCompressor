package textfilescompressor.model;

import org.junit.After;
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
        
        Compressor compressor = new Model(fileToCompressName, "", compressedOutFileName);
        Compressor decompressor = new Model("", fileToDecompressName, decompressedOutFileName);  
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compressFile method, of class Model.
     */
    @Test
    public void testCompressFile() throws Exception {
        System.out.println("compressFile");
        Model instance = null;
        boolean expResult = false;
        boolean result = instance.compressFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decompressFile method, of class Model.
     */
    @Test
    public void testDecompressFile() throws Exception {
        System.out.println("decompressFile");
        Model instance = null;
        boolean expResult = false;
        boolean result = instance.decompressFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
