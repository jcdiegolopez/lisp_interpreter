import java.util.List;

public class CondExpression extends Expression {
    private List<ConditionalExpression> conditions;

    public CondExpression(List<ConditionalExpression> conditions) {
        this.conditions = conditions;
    }

    @Override
    public Object evaluate(Environment environment) {
        for (ConditionalExpression condition : conditions) {
            if ((boolean) condition.getCondition().evaluate(environment)) {
                return condition.getResult().evaluate(environment);
            }
        }
        return null; // Si ninguna condición es verdadera, devolver null
    }
}
