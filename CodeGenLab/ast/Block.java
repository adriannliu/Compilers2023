package ast;
import java.util.ArrayList;
import environment.Environment;

/**
 * The Block class handles grouped Statements 
 * and executes them together
 *
 * @author Adrian Liu
 * @version 5/15/2024
 */
public class Block extends Statement
{
    private ArrayList<Statement> statements;

    /**
     * Constructor for objects of the Block Class
     * 
     * @param statements the list of statements within the block of statements
     */
    public Block(ArrayList<Statement> statements)
    {
        this.statements = statements;
    }
    
    /**
     * Executes the whole block by going through
     *  each statement in the list
     * 
     * @param env    the environment in which the classes execute code

     */
    @Override
    public void exec(Environment env)
    {
        for(Statement stat : statements)
        {
            stat.exec(env);
        }
    }

    /**
     * Outputs MIPS code that evaluates a block of statements by going through
     * each statement in the list
     * 
     * @param e     the emitter that outputs the MIPS code
     */
    public void compile(Emitter e)
    {
        for(Statement stat : statements)
        {
            stat.compile(e);
        }
    }
}
