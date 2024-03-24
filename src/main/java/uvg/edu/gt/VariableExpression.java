package uvg.edu.gt;

public class VariableExpression extends Expression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Environment environment) {
        return environment.lookupVariable(name);
    }

    public String toString() {
        return name;
    }

    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}
