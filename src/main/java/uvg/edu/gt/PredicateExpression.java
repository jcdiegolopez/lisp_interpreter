package uvg.edu.gt;

import java.util.List;
import java.util.Objects;

public class PredicateExpression extends Expression {
    private String predicate;
    private List<Expression> arguments;

    public PredicateExpression(String predicate, List<Expression> arguments) {
        this.predicate = predicate;
        this.arguments = arguments;
    }

    @Override
    public Object evaluate(Environment environment) {
        switch (predicate) {
            case "ATOM":
                return evaluateAtom();
            case "LIST":
                return evaluateList();
            case "EQUAL":
                return evaluateEqual();
            case "<":
                return evaluateLessThan();
            case ">":
                return evaluateGreaterThan();
            default:
                throw new IllegalArgumentException("Unknown predicate: " + predicate);
        }
        
    }

    private Object evaluateList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluateList'");
    }

    private Object evaluateAtom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluateAtom'");
    }

    private boolean evaluateEqual() {
        // Verificar si todos los argumentos son iguales entre sí
        if (arguments.size() < 2) {
            throw new IllegalArgumentException("EQUAL predicate (=) requires at least two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(null); // No se utiliza environment en este caso
        for (int i = 1; i < arguments.size(); i++) {
            Object nextValue = arguments.get(i).evaluate(null); // No se utiliza environment en este caso
            if (!Objects.equals(firstValue, nextValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateLessThan() {
        // Verificar si el primer argumento es menor que el segundo argumento
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Less than predicate (<) requires exactly two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(null); // No se utiliza environment en este caso
        Object secondValue = arguments.get(1).evaluate(null); // No se utiliza environment en este caso
        if (firstValue instanceof Integer && secondValue instanceof Integer) {
            return (Integer) firstValue < (Integer) secondValue;
        } else {
            throw new IllegalArgumentException("Less than predicate (<) requires integer arguments.");
        }
    }
    
    private boolean evaluateGreaterThan() {
        // Verificar si el primer argumento es mayor que el segundo argumento
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Greater than predicate (>) requires exactly two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(null); // No se utiliza environment en este caso
        Object secondValue = arguments.get(1).evaluate(null); // No se utiliza environment en este caso
        if (firstValue instanceof Integer && secondValue instanceof Integer) {
            return (Integer) firstValue > (Integer) secondValue;
        } else {
            throw new IllegalArgumentException("Greater than predicate (>) requires integer arguments.");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(predicate);
        for (Expression arg : arguments) {
            sb.append(" ");
            sb.append(arg.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}
