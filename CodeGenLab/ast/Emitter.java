/**
 * The Emitter class creates an output file and writes MIPS code into it based on instructions
 * from the AST. Code skeleton given.
 * 
 * @author Ms. Datar
 * @author Adrian Liu
 * @version 5/15/24
 */

package ast;
import java.io.*;

public class Emitter
{
    private PrintWriter out;
    private int count;

	//creates an emitter for writing to a new file with given name
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
            count = 0;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

	//prints one line of code to file (with non-labels indented)
    public void emit(String code)
    {
        if (!code.endsWith(":"))
			code = "\t" + code;
        out.println(code);
    }

	//closes the file.  should be called after all calls to emit.
    public void close()
    {
        out.close();
    }

    /**
     * Outputs MIPS code to push the element stored in the
     * given register onto the stack
     * 
     * @param reg   the register whose value is to be pushed onto the stack
     */
    public void emitPush(String reg)
    {
        emit("subu $sp $sp 4");
        emit("sw " + reg + " ($sp)");
    }
    
    /**
     * Outputs MIPS code to pop the element stored in the
     * given register off the stack
     * 
     * @param reg   the register whose value is to be popped off the stack
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + " ($sp)");
        emit("addu $sp $sp 4");
    }

    /**
     * Returns the next label ID, which increases by one each time
     * it is called. Used to ensure that each label in a program is unique.
     * 
     * @return the next label ID
     */
    public int nextLabelID()
    {
        count++;
        return count;
    }
}