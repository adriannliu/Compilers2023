package scanner;

import java.io.*;
import java.io.Reader;
import java.io.FileReader;

public class GenScannerTester
{
    /**
     * Tests the scanner class by creating a new Scanner object and 
     * calling testScanner with a given txt file.
     * 
     * @param args information from the command line
     * @throws FileNotFoundException if the file doesn't exist or can't be found
     * @throws ScanErrorException if the file contains an illegal character
     */
    public static void main(String[] args) throws FileNotFoundException, ScanErrorException
    {
        //FileInputStream inStream = new FileInputStream(new File(
                // "/Users/24adrianl/Dropbox/My Mac (Adrian's Computer)/Downloads/.txt"));
        Reader stream = new FileReader(new File("/Users/24adrianl/Dropbox/My Mac (Adrian's Computer)/Downloads/AdrianVivekJFlexLab/PhoneNumbers.txt"));
        Scannerabb lex = new Scannerabb(stream);
        testScanner(lex, stream);
    }

    /**
     * Runs through the file and calls the scanner on each token,
     * printing the result for each token.
     * 
     * @param scan the scanner
     * @param stream the file's input stream
     * @throws ScanErrorException
     */
    public static void testScanner(Scannerabb scan, Reader stream) throws ScanErrorException, FileNotFoundException
    {
        try
        {
            String token = scan.nextToken();
        while(!token.equals("EOF"))
        {
            System.out.println(token);
            token = scan.nextToken();
        }
        System.out.println(token);
        }
        catch (IOException e)
        {
            e.printStackTrace(); 
        }
        
    }
}
