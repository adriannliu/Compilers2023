package ast;
import environment.Environment;


/**
 * The Statement class is an abstract class which represents
 * all statements used to execute code
 * 
 * @author Adrian Liu
 * @version 5/15/2024
 */
public abstract class Statement 
{
    /**
     * A template for subclasses to use when executing expressions
     * 
     * @param env the environment in which the subclasses execute code
     */
    public abstract void exec(Environment env);

    /**
     * A template for subclasses to emit MIPS code 
     * 
     * @param e     the emitter that outputs the MIPS code
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}
