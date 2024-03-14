public class EqualExpression extends Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public EqualExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Verificar si las dos expresiones son iguales
        Object leftValue = leftExpression.evaluate(environment);
        Object rightValue = rightExpression.evaluate(environment);
        return leftValue.equals(rightValue);
    }
}
