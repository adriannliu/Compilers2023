package ast;
import environment.Environment;
/**
 * The Variable class handles the instatiation of variables, which are Expressions
 *
 * @author Adrian Liu
 * @version 5/15/2024
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructor for objects of the Variable class
     * @param name  the name of the variable
     */
    public Variable(String name)
    {
        this.name = name;
    }

    /**
     * Evaluates objects of the Variable class 
     * 
     * @param env the environment in which the classes evaluate code
     * @return the value of the variable

     */
    @Override
    public int eval(Environment env) 
    {
        return env.getVariable(name);
    }

    /**
     * Outputs MIPS code that loads a variable's value into register $v0
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        e.emit("la $t1 var" + name);
        e.emit("lw $v0 ($t1)");
    }
}
