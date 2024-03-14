public class AtomExpression extends Expression {
    private Expression expression;

    public AtomExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Verificar si la expresión es un átomo
        return expression.evaluate(environment) instanceof Atom;
    }
}
