package ast;
import environment.Environment;

/**
 * The Writeln class handles the printing of expressions
 * 
 * @author Adrian Liu
 * @version 5/15/2024
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for objects of the Writeln class
     * 
     * @param exp the expression to be printed
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * Prints the given expression
     * 
     * @param env the environment in which the classes execute code
     */
    @Override
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }

    /**
     * Outputs MIPS code to print a statement followed by a newline.
     * 
     * @param e     the emitter that outputs MIPS code
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");
        e.emit("la $a0, nL");
        e.emit("li $v0, 4");
        e.emit("syscall"); 
    }
}