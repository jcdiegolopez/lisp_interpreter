package uvg.edu.gt;

import java.util.List;

public class ConditionalExpression extends Expression {
    private List<Branch> branches;

    public ConditionalExpression(List<Branch> branches) {
        this.branches = branches;
    }

    /**
     * This Java function evaluates conditions in branches and returns the result of the first branch
     * with a true condition, throwing an exception if none are true.
     * 
     * @param environment The `environment` parameter in the `evaluate` method is typically an object
     * that represents the current state or context in which the evaluation is taking place. It may
     * contain variables, functions, or other information needed for the evaluation process. The
     * `evaluate` method seems to be iterating over different branches, evaluating
     * @return The `evaluate` method is returning the result of the first branch whose condition
     * evaluates to true.
     */
    @Override
public Object evaluate(Environment environment) {
    for (Branch branch : branches) {
        Object conditionResult = branch.getCondition().evaluate(environment);
        if (conditionResult instanceof Boolean && (Boolean) conditionResult) {
            return branch.getResult().evaluate(environment);
        }
    }
    // Si ninguna condición se cumple, lanzar una excepción
    throw new IllegalArgumentException("No branch condition evaluated to true.");
}

    /**
     * The `Branch` class represents a conditional branch with a condition and a corresponding result
     * expression.
     */
    static class Branch {
        private Expression condition;
        private Expression result;

        public Branch(Expression condition, Expression result) {
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

    /**
     * The toString method generates a string representation of a list of branches in a conditional
     * statement.
     * 
     * @return The `toString` method is returning a string representation of a list of branches in the
     * format of a conditional expression. The string starts with "(COND" and then iterates over each
     * branch, appending the condition and result of each branch within parentheses. Finally, the
     * string ends with a closing parenthesis.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(COND ");
        for (Branch branch : branches) {
            sb.append("(");
            sb.append(branch.getCondition().toString());
            sb.append(" ");
            sb.append(branch.getResult().toString());
            sb.append(") ");
        }
        sb.append(")");
        return sb.toString();
    }
}