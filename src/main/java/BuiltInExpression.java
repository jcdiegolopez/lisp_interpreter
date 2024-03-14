public class BuiltInExpression extends Expression {
    private Expression expression;

    public BuiltInExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar la expresión y devolver el resultado
        return expression.evaluate(environment);
    }
}
