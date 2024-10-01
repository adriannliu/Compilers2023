package ast;
import java.util.*;
import environment.Environment;

/**
 * The Program class serves as the root of the AST. Contains a list of
 * ProcedureDeclarations, a Statement, corresponding get methods, and an exec method.
 * 
 * @author Adrian Liu
 * @version 5/4/24
 */

public class Program {
    private List<ProcedureDeclaration> procedures;
    private Statement stmt;

    public Program(List<ProcedureDeclaration> procedures, Statement stmt)
    {
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

}