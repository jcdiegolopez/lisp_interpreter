package uvg.edu.gt;

import java.util.List;

public class ListExpression extends Expression {
    private List<Expression> expressions;

    public ListExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Object evaluate(Environment environment) {
        Object result = null;
        for (Expression exp : expressions) {
            result = exp.evaluate(environment);
        }
        return result; // Devuelve el valor de la última expresión evaluada
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Expression exp : expressions) {
            sb.append(exp.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}
