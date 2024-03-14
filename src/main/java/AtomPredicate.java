import java.util.List;
public class AtomPredicate extends Predicate {
    public AtomPredicate(List<Expression> arguments) {
        super(arguments);
        if (arguments.size() != 1) {
            throw new IllegalArgumentException("ATOM predicate expects exactly one argument");
        }
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar el argumento y comprobar si es un átomo (no una lista)
        Object argument = arguments.get(0).evaluate(environment);
        return !(argument instanceof List);
    }
}
