package ast;
import environment.Environment;

/**
 * The Number class represents numbers, which are Expressions.
 *
 * @author Adrian Liu
 * @version 5/15/2024
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
     * @param env   the environment in which the classes evaluate code
     * @return the value of the number
     */
    @Override
    public int eval(Environment env) 
    {
        return value;
    }

    /**
     * Outputs MIPS code into the output file that loads an immediate value
     * into register $v0
     * 
     * @param e     the emitter that ouputs the MIPS code
     */
    @Override
    public void compile(Emitter e)
    {
        e.emit("li $v0 " + value);
    }
}
