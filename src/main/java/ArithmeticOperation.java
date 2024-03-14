public class ArithmeticOperation extends Expression {
    private String operator;
    private Expression leftOperand;
    private Expression rightOperand;

    public ArithmeticOperation(String operator, Expression leftOperand, Expression rightOperand) {
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar los operandos y realizar la operación aritmética
        Object leftValue = leftOperand.evaluate(environment);
        Object rightValue = rightOperand.evaluate(environment);

        if (!(leftValue instanceof Number) || !(rightValue instanceof Number)) {
            throw new RuntimeException("Operands must be numbers");
        }

        double left = ((Number) leftValue).doubleValue();
        double right = ((Number) rightValue).doubleValue();

        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return left / right;
            default:
                throw new IllegalArgumentException("Invalid arithmetic operator: " + operator);
        }
    }
}
