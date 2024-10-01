package ast;
import environment.Environment;

/**
 * The Writeln class handles the printing of expressions
 * 
 * @author Adrian Liu
 * @version 4/09/2024
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for objects of the Writeln class
     * 
     * @param exp the expression to be printed
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * Prints the given expression
     * 
     * @param env the environment in which the classes execute code
     */
    @Override
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}