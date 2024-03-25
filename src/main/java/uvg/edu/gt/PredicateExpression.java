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
            return evaluateAtom(arguments.get(0), environment);
        case "LIST":
            return evaluateList(arguments.get(0), environment);
            case "EQUAL":
                return evaluateEqual(environment);
            case "=":
                return evaluateEqual(environment);
            case "<":
                return evaluateLessThan(environment);
            case ">":
                return evaluateGreaterThan(environment);
            case "<=":
                return evaluateLessOrEqualThan(environment);
            case ">=":
                return evaluateMoreOrEqualThan(environment);
            default:
                throw new IllegalArgumentException("Unknown predicate: " + predicate);
        }
        
    }

    private boolean evaluateList(Expression exp, Environment environment) {
        // Una lista es cualquier expresión que sea una instancia de ListExpression
        return exp instanceof ListExpression;
    }
    
    private boolean evaluateAtom(Expression exp, Environment environment) {
        // Un átomo es cualquier expresión que no sea una lista
        return !(exp instanceof ListExpression);
    }

    private boolean evaluateEqual(Environment environment) {
        // Verificar si todos los argumentos son iguales al primer argumento
        if (arguments.size() < 2) {
            throw new IllegalArgumentException("EQUAL predicate (=) requires at least two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(environment);
        for (int i = 1; i < arguments.size(); i++) {
            Object nextValue = arguments.get(i).evaluate(environment);
            if (!Objects.equals(firstValue, nextValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateLessThan(Environment environment) {
        // Verificar si el primer argumento es menor que el segundo argumento
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Less than predicate (<) requires exactly two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(environment);
        Object secondValue = arguments.get(1).evaluate(environment);
        if (firstValue instanceof Integer && secondValue instanceof Integer) {
            return (Integer) firstValue < (Integer) secondValue;
        } else {
            throw new IllegalArgumentException("Less than predicate (<) requires integer arguments.");
        }
    }
    
    private boolean evaluateGreaterThan(Environment environment) {
        // Verificar si el primer argumento es mayor que el segundo argumento
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Greater than predicate (>) requires exactly two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(environment);
        Object secondValue = arguments.get(1).evaluate(environment);
        if (firstValue instanceof Integer && secondValue instanceof Integer) {
            return (Integer) firstValue > (Integer) secondValue;
        } else {
            throw new IllegalArgumentException("Greater than predicate (>) requires integer arguments.");
        }
    }

    private boolean evaluateLessOrEqualThan(Environment environment) {
        // Verificar si el primer argumento es menor o igual que el segundo argumento
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Less than or equal to predicate (<=) requires exactly two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(environment);
        Object secondValue = arguments.get(1).evaluate(environment);
        if (firstValue instanceof Integer && secondValue instanceof Integer) {
            return (Integer) firstValue <= (Integer) secondValue;
        } else {
            throw new IllegalArgumentException("Less than or equal to predicate (<=) requires integer arguments.");
        }
    }

    private boolean evaluateMoreOrEqualThan(Environment environment) {
        // Verificar si el primer argumento es mayor o igual que el segundo argumento
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("Greater than or equal to predicate (>=) requires exactly two arguments.");
        }
        Object firstValue = arguments.get(0).evaluate(environment);
        Object secondValue = arguments.get(1).evaluate(environment);
        if (firstValue instanceof Integer && secondValue instanceof Integer) {
            return (Integer) firstValue >= (Integer) secondValue;
        } else {
            throw new IllegalArgumentException("Greater than or equal to predicate (>=) requires integer arguments.");
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
