import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> variables;
    private Map<String, Function> functions;
    private Map<String, Boolean> symbols;

    public Environment() {
        this.variables = new HashMap<>();
        this.functions = new HashMap<>();
        this.symbols = new HashMap<>();
    }

    public void addVariable(String name, Object value) {
        variables.put(name, value);
    }

    public Object getVariableValue(String name) {
        return variables.get(name);
    }

    public void setVariable(String name, Object value) {
        if (variables.containsKey(name)) {
            variables.put(name, value);
        } else {
            throw new RuntimeException("Variable '" + name + "' not defined");
        }
    }

    public void addFunction(String name, Function function) {
        functions.put(name, function);
    }

    public Function getFunction(String name) {
        return functions.get(name);
    }

    public void addSymbol(String name) {
        symbols.put(name, true);
    }

    public boolean isSymbol(String name) {
        return symbols.containsKey(name);
    }

    public Map<String, Object> getVariables(){
        return this.variables;
    }

        public Map<String, Boolean> getSymbols() {
        return symbols;
    }

    public Map<String, Function> getFunctions() {
        return functions;
    }


    // Otros métodos según sea necesario
}
