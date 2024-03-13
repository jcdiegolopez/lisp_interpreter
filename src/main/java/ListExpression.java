public class ListExpression extends Expression {
    private Expression expression;

    public ListExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Verificar si la expresión es una lista
        return expression.evaluate(environment) instanceof ListN;
    }
}
