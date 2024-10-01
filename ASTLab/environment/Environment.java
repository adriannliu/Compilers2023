package environment;
import java.util.HashMap;

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
    Environment env;

    /**
     * Constructor for objects of the Environment class
     */
    public Environment()
    {
        this.variables = new HashMap<String, Integer>();
    }

    /**
     * Adds variable to the environment map 
     * 
     * @param var   the variable to be stored
     * @param val   the value to be stored by the variable
     */
    public void setVariable(String var, int val)
    {
        variables.put(var, val);
    }
    

    /**
     * Returns the value or the variable contained within the
     * environment/map. 
     * 
     * @param var the name of the variable to be found in the map
     * @return the value of the variable
     */
    public int getVariable(String var)
    {
    
        return variables.get(var);
        
    }
}
