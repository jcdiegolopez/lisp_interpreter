package uvg.edu.gt;

/**
 * The `ComparisonExpression` class represents a comparison expression with an operator, left
 * expression, and right expression.
 */
public class ComparisonExpression extends Expression {
    private String operator;
    private Expression left;
    private Expression right;

    public ComparisonExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    /**
     * This function evaluates a comparison expression with operators like <, >, <=, >=, and =.
     * 
     * @param environment The `environment` parameter in the `evaluate` method is likely an object that
     * represents the current execution environment or context in which the comparison operation is
     * being evaluated. It may contain variables, functions, or other relevant information needed for
     * the evaluation of the comparison expression.
     * @return The method is evaluating a comparison expression between two integer values using the
     * specified operator ("<", ">", "<=", ">=", "=") and returning a boolean result based on the
     * comparison.
     */
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
