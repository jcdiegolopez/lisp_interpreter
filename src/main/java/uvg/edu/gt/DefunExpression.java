package uvg.edu.gt;

import java.util.List;

/**
 * The `DefunExpression` class represents a function definition with a name, parameters, and body
 * expressions.
 */
public class DefunExpression extends Expression {
    private String name;
    private List<String> parameters;
    private List<Expression> body;

    public DefunExpression(String name, List<String> parameters, List<Expression> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    /**
     * The function defines a new function in the given environment.
     * 
     * @param environment The `environment` parameter in the `evaluate` method is an object that
     * represents the current execution environment or context in which the function is being defined.
     * It is used to define the function within this environment so that it can be later invoked and
     * executed within the same context.
     * @return The `evaluate` method is returning `null`.
     */
    @Override
    public Object evaluate(Environment environment) {
        environment.defineFunction(name, parameters, body);
        return null;
    }

    /**
     * The `toString` function generates a string representation of a function definition in Lisp-like
     * syntax.
     * 
     * @return The `toString()` method is returning a string representation of a function definition in
     * a Lisp-like language. The returned string includes the function name, parameters, and body
     * expressions enclosed in parentheses.
     */
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
