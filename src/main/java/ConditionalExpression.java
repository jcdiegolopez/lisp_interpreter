public class ConditionalExpression {
    private Expression condition;
    private Expression result;

    public ConditionalExpression(Expression condition, Expression result) {
        this.condition = condition;
        this.result = result;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getResult() {
        return result;
    }
}
