package textfilescompressor.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 * Tests for all public methods in Model class
 */
public class ModelTest {
    
    private String fileToCompressName;
    private String compressedOutFileName;
    private String fileToDecompressName;
    private String decompressedOutFileName;
    
    public ModelTest() {
        
        this.fileToCompressName = "test.txt";
        this.compressedOutFileName = "out.txt";
        this.fileToDecompressName = "out.txt";
        this.decompressedOutFileName = "decompressed.txt";
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        Compressor compressor = new Model("test.txt", "", "out.txt");
        Compressor decompressor = new Model("", "out.txt", "decompressed.txt");        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
