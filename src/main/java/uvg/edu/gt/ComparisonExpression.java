package uvg.edu.gt;

public class ComparisonExpression extends Expression {
    private String operator;
    private Expression left;
    private Expression right;

    public ComparisonExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(Environment environment) {
        int leftValue = (int) left.evaluate(environment);
        int rightValue = (int) right.evaluate(environment);

        switch (operator) {
            case "<":
                return leftValue < rightValue;
            case ">":
                return leftValue > rightValue;
            case "<=":
                return leftValue <= rightValue;
            case ">=":
                return leftValue >= rightValue;
            case "=":
                return leftValue == rightValue;
            default:
                throw new IllegalArgumentException("Invalid comparison operator: " + operator);
        }
    }
}
