package uvg.edu.gt;

public class ArithmeticExpression extends Expression {
    private String operator;
    private Expression left;
    private Expression right;

    public ArithmeticExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(Environment environment) {
        int leftValue = (int) left.evaluate(environment);
        int rightValue = (int) right.evaluate(environment);
       

        switch (operator) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Invalid arithmetic operator: " + operator);
        }
    }

    public String toString() {
        return "(" + operator + " " + left.toString() + " " + right.toString() + ")";
    }
}
