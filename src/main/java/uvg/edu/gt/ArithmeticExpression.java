package uvg.edu.gt;

/**
 * The `ArithmeticExpression` class represents arithmetic expressions with an operator, left and right
 * operands, and can evaluate the expression based on the operator.
 */
public class ArithmeticExpression extends Expression {
    private String operator;
    private Expression left;
    private Expression right;

    public ArithmeticExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
        
    }

    // This code snippet is from a Java class named `ArithmeticExpression`.
    @Override
    public Object evaluate(Environment environment) {
        
        Integer rightValue;
        Integer leftValue;

        leftValue = (Integer) left.evaluate(environment);

        rightValue = (Integer) right.evaluate(environment);


        

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
