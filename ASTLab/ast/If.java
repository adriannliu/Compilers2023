package ast;
import environment.Environment;

/**
 * The If class handles "If" statements, which are Statements.
 * 
 * @author Adrian Liu
 * @version 4/09/2024
 *
 */
public class If extends Statement
{
    private Condition condition;
    private Statement stat1;
    private Statement stat2;

    /**
     * Constructor for objects of the If class when given a single statement.
     * Follows the "If-then" format.
     * 
     * @param cond  the conditional expression to be executed
     * @param s1    the first statement to be executed if conditional is true
     */
    public If(Condition cond, Statement s1)
    {
        condition = cond;
        stat1 = s1;
    }

    /**
     * Constructor for objects of the If class when given 2 statements.
     * Follows the "If-then-else" format.
     * 
     * @param cond   the conditional expression to be executed
     * @param s1     the first statement to be executed if conditional is true
     * @param s2     the second statement to be executed (else statement)
     */
    public If(Condition cond, Statement s1, Statement s2)
    {
        condition = cond;
        stat1 = s1;
        stat2 = s2;
    }

    /**
     * Executes an if statement
     * 
     * @param env   the environment in which the classes execute code
     */
    @Override
    public void exec(Environment env) 
    {
        int val = condition.eval(env);
        if (val == 1)
        {
            if (stat1 != null)
            {
                stat1.exec(env);
            }
        }
        else
        {
            if (stat2 != null)
            {
                stat2.exec(env);
            }
        }
    }
}