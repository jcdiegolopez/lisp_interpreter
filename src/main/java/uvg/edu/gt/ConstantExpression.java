package uvg.edu.gt;

/**
 * The ConstantExpression class represents an expression with a constant value that can be evaluated to
 * return the value itself.
 */
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
