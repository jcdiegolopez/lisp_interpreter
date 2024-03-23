package uvg.edu.gt;

public class SetqExpression extends Expression {
    private String variable;
    private Expression value;

    public SetqExpression(String variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public Object evaluate(Environment environment) {
        Object evaluatedValue = value.evaluate(environment);
        environment.defineVariable(variable, new ConstantExpression(evaluatedValue));
        return evaluatedValue;
    }

    public String toString() {
        return "(SETQ " + variable + " " + value.toString() + ")";
    }
}
