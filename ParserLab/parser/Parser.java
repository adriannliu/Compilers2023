package parser;

import scanner.*;
import scanner.Scanner;

import java.util.*;


/**
 * Parser is a simple top-down recursive descent parser that will execute
 * Pascal-like statements as it parses them. 
 * 
 * @version 3/11/2024
 * @author Adrian Liu
 */
public class Parser 
{
    private Scanner scan;
    private String currToken;
    private Map<String, Integer> map;

    /**
     * Constructor for objects of the Parser class
     * @param input the Scanner to use for the Parser
     * 
     * @throws ScanErrorException if currToken does not equal expected
     */
    public Parser(Scanner input)
    {
        scan = input;
        try
        {
            currToken = scan.nextToken();
            map = new HashMap<String, Integer>();
        }
        catch (ScanErrorException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the current token is what is expected. If so, advances
     * to the next token. If not,
     * throws a ScanErrorException.
     *  
     * @param expected the expected value of the current token.
     * 
     * @throws ScanErrorException if currToken does not equal expected
     */
    private void eat(String expected) throws ScanErrorException
    {
        if(expected.equals(currToken))
            currToken = scan.nextToken();
        else
            throw new ScanErrorException("Illegal Character -- Expected <" +
             expected + "> and found <" + currToken + ">.");
    }

    /**
     * Parses an integer
     * 
     * @return an integer representing the number in the string of
     * currToken
     * 
     * @throws ScanErrorException if currToken does not equal expected
     */
    private int parseNumber() throws ScanErrorException
    {
        int test = Integer.parseInt(currToken);
        eat(currToken);
        return test;
    }

    /**
     * Parses statements using WRITELN and prints the
     * value of the intended statement.
     * 
     * @throws ScanErrorException if currToken does not equal expected
     */
    public void parseStatement() throws ScanErrorException
    {
        // to print stuff
        if(currToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            int parsedStatement = parseExpression();
            eat(")");
            eat(";");
            System.out.println(parsedStatement);
        }
        else if(currToken.equals("BEGIN"))
        {
            eat("BEGIN");
            while(!currToken.equals("END"))
            {
                parseStatement();
            }
            eat("END");
            eat(";");
        }
        else if(!currToken.equals("EOF"))
        {
            String stat = currToken;
            eat(stat);
            eat(":=");
            Integer val = parseExpression();
            map.put(stat, val);
            eat(";");
        }
    }

    /**
     * Parses factors (e.g., "(" or "-") to handle expressions 
     * such as "(-(-3))".
     * 
     * @return the final value of the number after evaluating negative signs
     * @throws ScanErrorException if scanning fails
     */
    private int parseFactor() throws ScanErrorException
    {
        int factor = 0;
        if(currToken.equals("-"))
        {
            eat("-");
            factor = -parseFactor();
        }

        else if(currToken.equals("("))
        {
            eat("(");
            int parsedFactor = parseExpression();
            eat(")");
            factor = parsedFactor;
        }
        else if(map.containsKey(currToken))
        {
            factor= map.get(currToken);
            eat(currToken);
        }
        else 
        {
            factor = parseNumber();
        }

        return factor;
    }

    /**
     * Parses a single mathematical term that can include parentheses, negative signs,
     * multiplication, and division
     * 
     * @return the parsed term as an integer
     * @throws ScanErrorException if scanning goes wrong
     */
    private int parseTerm() throws ScanErrorException
    {
        int val = parseFactor();
        while (currToken.equals("*") || currToken.equals("/"))
        {
            String oldToken = currToken;
            eat(currToken);  
            if(oldToken.equals("*"))
                val *= parseFactor();
            else
                val /= parseFactor();
        }
        return val;
    }

    /**
     * Parses a mathematical expression that consists of multiple terms
     * which can be added or subtracted from each other
     * 
     * @return the value of the evaluted expression
     * @throws ScanErrorException if scanning goes wrong
     */
    private int parseExpression() throws ScanErrorException
    {
        int val = parseTerm();
        while (currToken.equals("+") || currToken.equals("-"))
        {
            if(currToken.equals("+"))
            {
                eat("+");
                val = val + parseTerm();
            }
            else if(currToken.equals("-"))
            {
                eat("-");
                val = val - parseTerm();
            }
        }
        return val;
    }

}
