package ast;
import java.util.*;
import environment.Environment;

/**
 * The Program class serves as the root of the AST. Contains a list of
 * ProcedureDeclarations, a Statement, corresponding get methods, and an exec method.
 * 
 * @author Adrian Liu
 * @version 5/15/24
 */

public class Program 
{
    private List<ProcedureDeclaration> procedures;
    private Statement stmt;
    private List<String> varNames;

    public Program(List<String> varNames, List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.varNames = varNames;
        this.procedures = procedures;
        this.stmt = stmt;
    }


    /**
     * Returns the list of ProcedureDeclarations contained in the Program
     * 
     * @return the list of ProcedureDeclarations
     */
    public List<ProcedureDeclaration> getProcedures()
    {
        return procedures;
    }
    
    /**
     * Returns the Statement contained in the Program
     * 
     * @return the Statement
     */
    public Statement getStatement()
    {
        return stmt;
    }

    /**
     * Executes the specified list of ProcedureDeclarations one by one in the
     * given environment. Then, the specified statement is executed in the given
     * environment and 0 is returned.
     * 
     * @param env the given environment where code is executed
     * @return 0
     */
    public int exec(Environment env)
    {
        for(ProcedureDeclaration proc : procedures)
        {
            proc.exec(env);
        }
        stmt.exec(env);
        return 0;
    }

    /**
     * Compiles a program with a given file name
     * 
     * @param name the name of the file to be compiled
     */
    public void compile(String name)
    {
        Emitter e = new Emitter(name);
    }

    /**
     * Emits MIPS code to compile a program with a 
     * specific code structure
     * 
     * @param e  the emitter that outputs the MIPS code to the file
     */
    public void compile(Emitter e)
    {
        e.emit(".data");
        e.emit("nL:    .asciiz    \"\\n\"");
        for(String v: varNames)
        {
            e.emit("var" + v + ":\t .word \t 0");
        }
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main: ");
        stmt.compile(e);
        e.emit("li $v0 10");
        e.emit("syscall");
        e.close();
    }
}