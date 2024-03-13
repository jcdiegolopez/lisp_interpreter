import java.util.List;

public class EqualPredicate extends Predicate {
    public EqualPredicate(List<Expression> arguments) {
        super(arguments);
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("EQUAL predicate expects exactly two arguments");
        }
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar los dos argumentos y comprobar si son iguales
        Object arg1 = arguments.get(0).evaluate(environment);
        Object arg2 = arguments.get(1).evaluate(environment);
        return arg1.equals(arg2);
    }
}
