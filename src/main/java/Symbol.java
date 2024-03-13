import java.util.List;

public class Symbol extends Expression {
    private String name;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Buscar el valor del símbolo en el entorno y devolverlo

        Function value = environment.getFunction(name);
        if (value == null) {
            throw new RuntimeException("Variable '" + name + "' not defined");
        }
        System.out.println(name);
        return value;
    }
}

/*
public class Symbol extends Expression {
    private String name;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Buscar el valor del símbolo en el entorno y devolverlo
        Object value = environment.getVariableValue(name);
        if (value == null) {
            throw new RuntimeException("Variable '" + name + "' not defined");
        }
        return value;
    }
}
*/
