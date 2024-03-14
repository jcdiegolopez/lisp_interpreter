public class SetQExpression extends Expression {
    private String variableName;
    private Expression valueExpression;

    public SetQExpression(String variableName, Expression valueExpression) {
        this.variableName = variableName;
        this.valueExpression = valueExpression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar el valor de la expresión y asignarlo a la variable en el entorno
        Object value = valueExpression.evaluate(environment);
        environment.setVariable(variableName, value);
        return value;
    }
}
