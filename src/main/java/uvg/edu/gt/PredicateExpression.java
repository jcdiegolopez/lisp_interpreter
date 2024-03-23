package uvg.edu.gt;

import java.util.List;

public class PredicateExpression extends Expression {
    private String operator;
    private List<Expression> arguments;

    public PredicateExpression(String operator, List<Expression> arguments) {
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Implementa la evaluación del predicado según el operador y los argumentos.
        // Por simplicidad, no lo he implementado en este ejemplo.
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(operator);
        for (Expression exp : arguments) {
            sb.append(" ");
            sb.append(exp.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}
