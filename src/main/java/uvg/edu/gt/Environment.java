package uvg.edu.gt;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, LispExpression> variables;
    private Map<String, LispFunction> functions;

    public Environment() {
        variables = new HashMap<>();
        functions = new HashMap<>();
    }

    // Implementar métodos para almacenar y recuperar variables y funciones.
}
