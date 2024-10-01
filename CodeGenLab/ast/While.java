package ast;
import environment.Environment;

/**
 * The While class handles while loops, which are Statements.
 * 
 * @author Adrian Liu
 * @version 5/15/2024
 */
public class While extends Statement
{
    private Condition conditional;
    private Statement stat1;

    /**
     * Constructor for objects of the While class
     * @param cond   the conditional expression to be executed
     * @param s1     the statement to be executed if conditional is true
     */
    public While(Condition cond, Statement s1)
    {
        conditional = cond;
        stat1 = s1;
    }

    /**
     * Executes the while loop
     * 
     * @param env the environment in which the classes execute code

     */
    @Override
    public void exec(Environment env) 
    {
        int ret = conditional.eval(env);
        while (ret == 1)
        {
            if (stat1 != null)
            {
                stat1.exec(env);
            }
            ret = conditional.eval(env);
        }
    }

     /**
     * Outputs code that compiles a while loop, using labelID to keep track
     * of the statement being compiled and ensure that each subroutine has a 
     * unique name.
     * 
     * @param e the emitter that emits the MIPS code
     */
    public void compile(Emitter e)
    {
        int id = e.nextLabelID();
        e.emit("while" + id + ":");
        conditional.compile(e, "endwhile" + id);
        stat1.compile(e);
        e.emit("j while" + id);
        e.emit("endwhile" + id + ":");
    }
}