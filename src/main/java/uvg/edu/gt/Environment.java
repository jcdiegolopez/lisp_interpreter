package uvg.edu.gt;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    Map<String, LispExpression> variables;
    Map<String, LispFunction> functions;

    public Environment() {
        variables = new HashMap<>();
        functions = new HashMap<>();
    }

    // Método para almacenar una variable en el entorno.
    public void setVariable(String name, LispExpression value) {
        variables.put(name, value);
    }

    // Método para obtener el valor de una variable del entorno.
    public LispExpression getVariable(String name) {
        return variables.get(name);
    }

    // Método para almacenar una función en el entorno.
    public void setFunction(String name, LispFunction function) {
        functions.put(name, function);
    }

    // Método para obtener una función del entorno.
    public LispFunction getFunction(String name) {
        return functions.get(name);
    }
}
