package uvg.edu.gt;

import java.util.List;

public class ConditionalExpression extends Expression {
    private List<Branch> branches;

    public ConditionalExpression(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public Object evaluate(Environment environment) {
        for (Branch branch : branches) {
            System.out.println("evaluando ramas");
            Object conditionResult = branch.getCondition().evaluate(environment);
            if (conditionResult instanceof Boolean && (Boolean) conditionResult) {
                System.out.println("evaluando booleanos");
                return branch.getResult().evaluate(environment);
            }
        }
        return null; // No branch matched, return null or throw an exception
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
