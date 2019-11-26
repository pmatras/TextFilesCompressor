package textfilescompressorserver.servermodel;

/**
 *
 * @author Piotr Matras
 * @version 1.0
 */
public class SingleInstanceOfServerFileCompressorGuard {
    
    private static ServerFilesCompressor filesCompressor;
    
    public static ServerFilesCompressor getFilesCompressor(String fileToCompressName, String fileToDecompressName, String outputFileName) {
        
        if(filesCompressor == null) {
            filesCompressor = new ServerFilesCompressor(fileToCompressName, fileToDecompressName, outputFileName);
        }
        
        return filesCompressor;
    }
    
}
