package ast;
import environment.Environment;

/**
 * The Number class represents numbers, which are Expressions.
 *
 * @author Adrian Liu
 * @version 4/09/2024
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor for objects of the Number class
     * @param num   the number
     */
    public Number(int num)
    {
        value = num;
    }

    /**
     * Evaluates the given number
     * 
     * @param env the environment in which the classes evaluate code
     * @return the value of the number
     */
    @Override
    public int eval(Environment env) 
    {
        return value;
    }
}
