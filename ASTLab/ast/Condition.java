package ast;
import environment.Environment;

/**
 * The Condition class handles expressions involving conditional operands
 * such as "==" or "<=", which are Expressions.
 * 
 * @author Adrian Liu
 * @version 4/09/2024
 */
public class Condition extends Expression
{
    private String op;
    private Expression left;
    private Expression right;

    /**
     * Constructor for objects of the Condition class
     * 
     * @param operator  the operator the expressions will be evaluated with
     * @param left1     the expression left of the operator
     * @param right1    the expression right of the operator
     */
    public Condition(String operator, Expression left1, Expression right1)
    {
        op = operator;
        left = left1;
        right = right1;
    }

    /**
     * Evaluates conditional expressions
     * @param env    the environment in which the classes evaluate code
     * @return 0 or 1; 0 when expression is evaluated to be false, and 1 when evaluated to be true
     */
    @Override
    public int eval(Environment env)
    {
        int val1 = left.eval(env);
        int val2 = right.eval(env);
        int ret = 0;
        if (op.equals("=="))
        {
            if (val1 == val2)
            {
                ret = 1;
            }
        }
        else if (op.equals("<>"))
        {
            if (val1 != val2)
            {
                ret = 1;
            }
        }
        else if (op.equals(">"))
        {
            if (val1 > val2)
            {
                ret = 1;
            }
        }
        else if (op.equals("<"))
        {
            if (val1 < val2)
            {
                ret = 1;
            }
        }
        else if (op.equals("<="))
        {
            if (val1 <= val2)
            {
                ret = 1;
            }
        }
        else if (op.equals(">="))
        {
            if (val1 >= val2)
            {
                ret = 1;
            }
        }
        return ret;
    }
}