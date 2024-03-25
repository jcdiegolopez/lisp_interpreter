package uvg.edu.gt;

import java.util.List;

/**
 * A class that represents a list of expressions to be evaluated.
 */
public class ListExpression extends Expression {
    // A list of expressions contained in this list expression.
    private List<Expression> expressions;

    /**
     * Constructs a new ListExpression with the given list of expressions.
     *
     * @param expressions The list of expressions to be included in this list expression.
     */
    public ListExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    /**
     * Evaluates each expression in this list expression in order, returning the result
     * of the last expression evaluated.
     *
     * @param environment An environment containing variable bindings to use during evaluation.
     * @return The result of evaluating the last expression in this list expression.
     */
    @Override
    public Object evaluate(Environment environment) {
        Object result = null;
        for (Expression exp : expressions) {
            result = exp.evaluate(environment);
        }
        return result; // Returns the result of the last expression evaluated.
    }

    /**
     * Returns a string representation of this list expression in prefix notation,
     * with each expression separated by a space.
     *
     * @return A string representation of this list expression in prefix notation.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (Expression exp : expressions) {
            sb.append(exp.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}