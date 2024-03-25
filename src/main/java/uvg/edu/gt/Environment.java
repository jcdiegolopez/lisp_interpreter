package uvg.edu.gt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    private Map<String, Expression> variables = new HashMap<>();
    private Map<String, Function> functions = new HashMap<>();

    // The `public Environment(Environment parent)` constructor in the `Environment` class is creating
    // a new environment based on a parent environment. It copies the variables and functions from the
    // parent environment into the new environment by creating new HashMap objects initialized with the
    // contents of the parent's variables and functions.
    public Environment(Environment parent) {
        this.variables = new HashMap<>(parent.variables);
        this.functions = new HashMap<>(parent.functions);
    }

    // The `public Environment()` constructor in the `Environment` class is initializing the
    // environment by setting a default variable. In this case, it is setting a variable named "t" with
    // a boolean value of `true` by creating a new `BooleanExpression` object with the value `true` and
    // storing it in the `variables` map.
    public Environment() {
        variables.put("t",  new BooleanExpression(true));
    }

   /**
    * The defineVariable function in Java stores a variable name and its corresponding expression in a
    * map.
    * 
    * @param name The `name` parameter is a String that represents the name of the variable being
    * defined.
    * @param value The `value` parameter in the `defineVariable` method represents an `Expression`
    * object that will be associated with the variable name in the `variables` map.
    */
    public void defineVariable(String name, Expression value) {
        variables.put(name, value);
    }

    /**
     * The `defineFunction` method creates a new function with a given name, parameters, and body, and
     * stores it in a map of functions.
     * 
     * @param name The name parameter is a String that represents the name of the function being
     * defined.
     * @param parameters The `defineFunction` method takes three parameters:
     * @param body The `body` parameter in the `defineFunction` method represents a list of expressions
     * that make up the body of the function. These expressions typically contain the logic and
     * operations that the function will perform when it is called.
     */
    public void defineFunction(String name, List<String> parameters, List<Expression> body) {
        Function function = new Function(name, parameters, body);
        functions.put(name, function);
    }

    /**
     * The function `lookupVariable` retrieves an `Expression` object associated with a given variable
     * name from a map called `variables`.
     * 
     * @param name The `name` parameter is a `String` representing the name of the variable to look up
     * in the `variables` map.
     * @return The method `lookupVariable` is returning an `Expression` object corresponding to the
     * variable with the given name from the `variables` map.
     */
    public Expression lookupVariable(String name) {
        return variables.get(name);
    }

    /**
     * The `lookupFunction` method returns a function based on the provided name from a map of
     * functions.
     * 
     * @param name The `name` parameter is a `String` type, which represents the name of a function
     * that you want to look up in the `functions` map.
     * @return The `lookupFunction` method is returning a `Function` object corresponding to the given
     * `name` from the `functions` map.
     */
    public Function lookupFunction(String name) {
        return functions.get(name);
    }

    /**
     * The function `getFunctions` returns a Map of String keys to Function values.
     * 
     * @return The `getFunctions` method is returning a `Map` with keys of type `String` and values of
     * type `Function`.
     */
    public Map<String, Function> getFunctions() {
        return functions;
    }

    /**
     * The function `getVariables` returns a map of strings to expressions representing variables.
     * 
     * @return The method `getVariables()` is returning a `Map` where the key is a `String` and the
     * value is an `Expression`.
     */
    public Map<String, Expression> getVariables() {
        return variables;
    }
}