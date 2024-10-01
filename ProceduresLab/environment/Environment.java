package environment;
import java.util.HashMap;

import ast.ProcedureDeclaration;

/**
 * The Environment class creates environments that hold instructions
 * on how to execute code
 *
 * @author Adrian Liu
 * @version 4/09/2024
 */
public class Environment
{
    private HashMap<String, Integer> variables;
    private HashMap<String, ProcedureDeclaration> procedures;
    private Environment parent;


    /**
     * Constructor for objects of the Environment class
     */
    public Environment()
    {
        this.variables = new HashMap<String, Integer>();
        this.procedures = new HashMap<String, ProcedureDeclaration>();
    }

    /**
     * Constructor for objects of the Environment class when given a parent Environment
     * 
     * @param parent the parent Environment
     */
    public Environment(Environment parent)
    {
        this.variables = new HashMap<String, Integer>();
        this.procedures = new HashMap<String, ProcedureDeclaration>();
        this.parent = parent;
    }

    /**
     * Stores a variable in a HashMap. If called on a child Environment where
     * the variable has not been declared in the current Environment,
     * the variable will be set in the highest hierarchy where it is declared, up until
     * the global environment. If never declared previously, the variable's value
     * will be set in the current Environment.
     * 
     * @param var   the variable to be stored in the HashMap
     * @param val   the value to be stored by the variable
     */
    public void setVariable(String var, int val)
    {
        if(variables.containsKey(var))
        {
            variables.put(var, val);
            return;
        }
        else 
        {
            if(parent != null)
            {
                if(parent.variables.containsKey(var))
                {
                    parent.variables.put(var, val);
                    return;
                }
            }
        }
        variables.put(var, val);    
    }

    /**
     * Adds a variable specifically to the current environment's HashMap 
     * 
     * @param var   the variable to be stored
     * @param val   the value to be stored by the variable
     */
    public void declareVariable(String var, int val)
    {
        variables.put(var, val);
    }
    
    /**
     * Returns the value or the variable contained within the
     * environment/map. If the variable is contained in the parent Environment,
     * its value is returned from the parent Environment; Otherwise, it is returned
     * from the current environment.
     * 
     * @param variable the name of the variable that is searched for in the map
     * @return the value of the variable
     */
    public int getVariable(String variable)
    {
        if(variables.containsKey(variable))
        {
            return variables.get(variable);
        }
        else 
        {
            if(parent != null)
            {
                if(parent.variables.containsKey(variable))
                {
                    return parent.variables.get(variable);
                }
            }
        }
        return variables.get(variable);
    }

    /**
     * Inserts a ProcedureDeclaration into the HashMap with name var
     * 
     * @param var   the name (or key) of the ProcedureDeclaration
     * @param dec   the ProcedureDeclaration that is inserted into the HashMap
     */
    public void setProcedure(String var, ProcedureDeclaration dec)
    {
        procedures.put(var, dec);
    }

    /**
     * Returns the ProcedureDeclaration with the given name in the HashMap
     * 
     * @param search the name of the ProcedureDeclaration to search for
     * @return the ProcedureDeclaration with name search
     */
    public ProcedureDeclaration getProcedure(String search)
    {
        return procedures.get(search);
    }
    

    /**
     * Returns the parent environment of the current Environment
     * 
     * @return the parent Environment
     */
    public Environment getParent()
    {
        return parent;
    }
}
