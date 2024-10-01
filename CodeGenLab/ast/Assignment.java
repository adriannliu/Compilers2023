package ast;
import environment.Environment;

/**
 * Represents a subclass of statement in which a variable
 * is assigned to an expression in the given environment
 *
 * @author Adrian Liu
 * @version 5/15/2024
 */
public class Assignment extends Statement
{
    private String variable;
    private Expression expression;

    /**
     * Constructor for objects of the Assignment class
     * 
     * @param variable      the variable in the statement
     * @param expression    the value assigned to the variable
     */
    public Assignment(String variable, Expression expression)
    {
        this.variable = variable;
        this.expression = expression;
    }

    /**
     * Executes objects of the Assignment class by assigning
     * the value to the variable in the given environment
     * 
     * @param env           the environment in which the class executes code
     */
    @Override
    public void exec(Environment env)
    {
        env.setVariable(variable, expression.eval(env));
    }

    /**
     * Outputs MIPS code that assigns an expression to a variable
     * using the saveword command
     * 
     * @param e             the emitter that outputs MIPS code
     */
    @Override
    public void compile(Emitter e)
    {
        expression.compile(e);
        e.emit("la $t1 var" + variable);
        e.emit("sw $v0, ($t1)");
    }
}
