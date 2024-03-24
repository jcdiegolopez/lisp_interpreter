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
        System.out.println(left.getClass());
        System.out.println(right.getClass());
        Integer rightValue;
        Integer leftValue;
        if(left instanceof VariableExpression){
            ConstantExpression leftC = (ConstantExpression) left.evaluate(environment);
            leftValue = (Integer) leftC.evaluate(environment) ;
        }else{
            leftValue = (Integer) left.evaluate(environment);
        }

        if(right instanceof VariableExpression){
            ConstantExpression rightC = (ConstantExpression) right.evaluate(environment);
            rightValue = (Integer) rightC.evaluate(environment) ;
        }else{
            rightValue = (Integer) right.evaluate(environment);
        }

        

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
