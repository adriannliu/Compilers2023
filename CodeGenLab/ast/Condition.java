package ast;
import environment.Environment;

/**
 * The Condition class handles expressions involving conditional operands
 * such as "==" or "<=", which are Expressions.
 * 
 * @author Adrian Liu
 * @version 5/15/2024
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
     * @return 0 when expression is evaluated to be false, and 1 when evaluated to be true
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

    /**
     * Outputs MIPS code to evaluate a Condition expression, jumping 
     * to different subroutines based on evaluation of Conditions. 
     * 
     * @param e         the emitter which outputs the MIPS code
     * @param target    the target to which the program jumps
     */
    public void compile(Emitter e, String target)
    {
        left.compile(e);
        e.emitPush("$v0");
        right.compile(e);
        e.emitPop("$t1");
        if (op.equals("="))
        {
            e.emit("bne $t1, $v0, " + target);
        }
        else if (op.equals("<>"))
        {
            e.emit("beq $t1, $v0, " + target);
        }
        else if (op.equals("<"))
        {
            e.emit("bge $t1, $v0, " + target);
        }
        else if (op.equals(">"))
        {
            e.emit("ble $t1, $v0, " + target);
        }
        else if (op.equals("<="))
        {
            e.emit("bgt $t1, $v0, " + target);
        }
        else if (op.equals(">="))
        {
            e.emit("blt $t1, $v0, " + target);
        }
    }
}