package uvg.edu.gt;

/**
 * The `BooleanExpression` class represents a boolean value in Java and can be evaluated to return its
 * value.
 */
public class BooleanExpression extends Expression {
    private boolean value;
    BooleanExpression(boolean bool){
        this.value = bool;
    }
    @Override
    public Object evaluate(Environment environment) {
        return value;
    }
}
