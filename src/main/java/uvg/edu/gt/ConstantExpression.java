package uvg.edu.gt;

public class ConstantExpression extends Expression {
    private Object value;

    public ConstantExpression(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment environment) {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
