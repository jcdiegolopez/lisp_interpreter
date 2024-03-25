package uvg.edu.gt;

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
