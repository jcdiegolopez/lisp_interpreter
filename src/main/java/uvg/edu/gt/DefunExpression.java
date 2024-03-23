package uvg.edu.gt;

import java.util.List;

public class DefunExpression extends Expression {
    private String name;
    private List<String> parameters;
    private List<Expression> body;

    public DefunExpression(String name, List<String> parameters, List<Expression> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment environment) {
        environment.defineFunction(name, parameters, body);
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(DEFUN ");
        sb.append(name);
        sb.append(" (");
        for (String param : parameters) {
            sb.append(param);
            sb.append(" ");
        }
        sb.append(") ");
        for (Expression exp : body) {
            sb.append(exp.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}
