package parser;
import scanner.*;
import scanner.Scanner;
import ast.*;
import java.util.*;


/**
 * Parser is a simple top-down recursive descent parser that will execute
 * Pascal-like statements as it parses them. Updated for AST lab.
 * 
 * @version 5/15/2024
 * @author Adrian Liu
 */
public class Parser 
{
    private Scanner scan;
    private String currToken;

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
    private Expression parseNumber() throws ScanErrorException
    {
        int test = Integer.parseInt(currToken);
        eat(currToken);
        return new ast.Number(test);
    }

    /**
     * Parses statements using WRITELN and returns a statement
     * instead of printing the parsed statement. Updated to work with AST lab.
     * 
     * @throws ScanErrorException if currToken does not equal expected
     * @return the statement that is parsed
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if(currToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(exp);
        }
        else if(currToken.equals("BEGIN"))
        {
            eat("BEGIN");
            ArrayList<Statement>list = new ArrayList<>();
            while(!currToken.equals("END"))
            {
                list.add(parseStatement());
            }
            Block block = new Block(list);
            eat("END");
            eat(";");
            return block;
        }
        
        else if(currToken.equals("IF"))
        {
            eat("IF");
            Condition cond = parseConditional();
            eat("THEN");
            Statement stat = parseStatement();
            return new If(cond, stat);
        }
        else if(currToken.equals("WHILE"))
        {
            eat("WHILE");
            Condition cond = parseConditional();
            eat("DO");
            Statement stat = parseStatement();
            return new While(cond, stat);
        }
        else
        {
            String curr = currToken;
            eat(curr);
            eat(":=");
            Assignment assignment = new Assignment(curr, parseExpression());
            eat(";");
            return assignment;
        }
    }

    /**
     * Parses factors (e.g., "(" or "-") to handle expressions 
     * such as "(-(-3))".
     * 
     * @return the final value of the number after evaluating negative signs
     * @throws ScanErrorException if scanning fails
     */
    private Expression parseFactor() throws ScanErrorException
    {
        if(currToken.equals("("))
        {
            eat("(");
            Expression expr = parseExpression();
            eat(")");
            return expr;
        }
        else if(currToken.equals("-"))
        {
            eat("-");
            return new BinOp("-", new ast.Number(0), parseFactor());
        }
        else
        {
            String token = currToken;
            try
            {
                int val = Integer.parseInt(currToken);
                eat(currToken);
                ast.Number num = new ast.Number(val);
                return num;
            }
            catch(Exception e)
            {
                eat(currToken);
                if(currToken.equals("("))
                {
                    eat("(");
                    ArrayList<Expression> params = new ArrayList();
                    while(!currToken.equals(")"))
                    {
                        params.add(parseExpression());
                        if(currToken.equals(","))
                        {
                            eat(",");
                        }
                    }
                    eat(")");
                    return new ProcedureCall(token, params);
                }
                else
                {
                    return new Variable(token);
                }
            }
        }
    }

    /**
     * Parses a single mathematical term that can include parentheses, negative signs,
     * multiplication, and division
     * 
     * @return the parsed term as an integer
     * @throws ScanErrorException if scanning goes wrong
     */
    private Expression parseTerm() throws ScanErrorException
    {
        Expression val = parseFactor();
        while (currToken.equals("*") || currToken.equals("/"))
        {
            String oldToken = currToken;
            eat(currToken);  
            if(oldToken.equals("*"))
                val = new BinOp("*", val, parseFactor());
            else
                val = new BinOp("/", val, parseFactor());
        }
        return val;
    }

    /**
     * Parses a mathematical expression that consists of multiple terms
     * which can be added or subtracted from each other by calling 
     * BinOp for each operation.
     * 
     * @return the value of the evaluted expression
     * @throws ScanErrorException if scanning goes wrong
     */
    private Expression parseExpression() throws ScanErrorException
    {
        Expression val = parseTerm();
        while (currToken.equals("+") || currToken.equals("-"))
        {
            if(currToken.equals("+"))
            {
                eat("+");
                val = new BinOp("+", val, parseTerm());
            }
            else if(currToken.equals("-"))
            {
                eat("-");
                val = new BinOp("-", val, parseTerm());
            }
        }
        return val;
    }

    /**
     * Parses a conditional object
     * @return the conditional object consisting of the parsed expression and comparative operator
     * @throws ScanErrorException if scanning goes wrong
     */
    public Condition parseConditional() throws ScanErrorException
    {
        Expression exp1 = parseExpression();
        String compOp = parseCompOp();
        Expression exp2 = parseExpression();
        return new Condition(compOp, exp1, exp2);
    }

    /**
     * Eats the comparative operator and returns its string version
     * @return the string version of the compOp
     * @throws ScanErrorException if scanning goes wrong
     */
    public String parseCompOp() throws ScanErrorException
    {
        if(currToken.equals("="))
        {
            eat("=");
            return "=";
        }
        else if(currToken.equals("<>"))
        {
            eat("<>");
            return "<>";
        }
        else if(currToken.equals("<"))
        {
            eat("<");
            return "<";
        }
        else if(currToken.equals(">"))
        {
            eat(">");
            return ">";
        }
        else if(currToken.equals("<="))
        {
            eat("<=");
            return "<=";
        }
        else
        {
            eat(">=");
            return ">=";
        }
    }

    /**
     * Parses and eats a list of ProcedureDeclarations and a list of  varNames
     * given in the body of text after a Procedure or a VAR. Continues parsing
     * tokens as long as the current token is PROCEDURE or VAR. Returns
     * a Program object made with the list of VARs, ProcedureDeclarations, and the
     * parsed statement.
     * 
     * @return the Program object
     * @throws ScanErrorException if scanning goes wrong
     */
    public Program parseProgram() throws ScanErrorException
    {
        ArrayList<String> vars = new ArrayList<String>();
        while(currToken.equals("VAR"))
        {
            eat("VAR");
            while (!currToken.equals(";"))
            {
                vars.add(currToken);
                eat(currToken);
                if(currToken.equals(","))
                {
                    eat(",");
                }
            }
            eat(";");
        }
        List<ProcedureDeclaration> pd = new ArrayList<ProcedureDeclaration>();
        while(currToken.equals("PROCEDURE"))
        {
            eat("PROCEDURE");
            String name = currToken;
            eat(name);
            eat("(");
            ArrayList<String> params = new ArrayList();
            while(!currToken.equals(")"))
            {
                String newparam = currToken;
                eat(newparam);
                params.add(newparam);
                if(currToken.equals(","))
                {
                    eat(",");
                }
            }
            eat(")");
            eat(";");
            pd.add(new ProcedureDeclaration(name, parseStatement(), params));
        }
        Statement stmt = parseStatement();
        return new Program(vars, pd, stmt);
    }
}
