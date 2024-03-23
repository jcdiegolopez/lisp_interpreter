package uvg.edu.gt;

import java.util.List;

public class ConditionalExpression extends Expression {
    private List<Branch> branches;

    public ConditionalExpression(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Implementa la evaluación de la expresión condicional COND.
        // Por simplicidad, no lo he implementado en este ejemplo.
        return null;
    }

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
