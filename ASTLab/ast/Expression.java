package ast;
import environment.Environment;

/**
 * Expression class is the abstract class representing
 * all expressions that are used in executing code 
 *
 * @author Adrian Liu
 * @version 4/09/2024
 */
public abstract class Expression
{
    /**
     * A template for subclasses to use when evaluating expressions
     * 
     * @param env   the environment in which the classes evaluate code
     * @return the value of the given object of the particular subclass
     */
    public abstract int eval(Environment env);
}

