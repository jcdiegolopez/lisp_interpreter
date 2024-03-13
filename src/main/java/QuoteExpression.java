public class QuoteExpression extends Expression {
    private Expression expression;

    public QuoteExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Devuelve la expresión sin evaluar
        return expression;
    }
}
