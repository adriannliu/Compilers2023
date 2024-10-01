import parser.Parser;
import scanner.*;

import java.io.*;

public class Main
{
    /**
     * Tests scanner using an input file
     * 
     * @param args          info for the command line
     * @throws IOException  IOException if FileIO goes wrong
     * @throws ScanErrorException if scanning goes wrong
     */
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        FileInputStream fileInputStream = new FileInputStream(new File(
                "/Users/24adrianl/Desktop/compilers/ParserLabAdrianLiu/src/ParserTest4.txt"));
        Scanner scanner = new Scanner(fileInputStream);
        Parser parser = new Parser(scanner);
        parser.parseStatement();
        parser.parseStatement();
        parser.parseStatement();
    }
}