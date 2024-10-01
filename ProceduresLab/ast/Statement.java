package ast;
import environment.Environment;

/**
 * The Statement class is an abstract class which represents
 * all statements used to execute code
 * 
 * @author Adrian Liu
 * @version 4/09/2024
 */
public abstract class Statement 
{
    /**
     * A template for subclasses to use when executing expressions
     * 
     * @param env the environment in which the subclasses execute code
     */
    public abstract void exec(Environment env);
}
