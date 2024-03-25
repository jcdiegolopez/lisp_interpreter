package uvg.edu.gt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    private Map<String, Expression> variables = new HashMap<>();
    private Map<String, Function> functions = new HashMap<>();



    public void defineVariable(String name, Expression value) {
        variables.put(name, value);
    }

    public void defineFunction(String name, List<String> parameters, List<Expression> body) {
        Function function = new Function(name, parameters, body);
        functions.put(name, function);
    }

    public Expression lookupVariable(String name) {
        return variables.get(name);
    }

    public Function lookupFunction(String name) {
        return functions.get(name);
    }

    public Map<String, Function> getFunctions() {
        return functions;
    }

    public Map<String, Expression> getVariables() {
        return variables;
    }
}
