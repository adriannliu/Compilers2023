import environment.Environment;
import parser.Parser;
import scanner.*;

import java.io.*;

/**
 * Runs testers for the AST lab using an input tester file
 *
 * @author  Adrian Liu
 * @version 5/15/2024
 */
public class Main
{
    /**
     * Tests the scanner and parser given input files
     * @param args          info from the command line
     * @throws IOException  IOException if FileIO goes wrong
     * @throws ScanErrorException if scanning goes wrong
     */
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        FileInputStream fileInputStream = new FileInputStream(new File(
                "/Users/24adrianl/Desktop/compilers/CodeGenLabAdrianLiu/src/test.txt"));
        Scanner scanner = new Scanner(fileInputStream);
        Parser parser = new Parser(scanner);

        parser.parseProgram().compile("output.asm");
    }
}
