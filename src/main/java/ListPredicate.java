import java.util.List;
public class ListPredicate extends Predicate {
    public ListPredicate(List<Expression> arguments) {
        super(arguments);
        if (arguments.size() != 1) {
            throw new IllegalArgumentException("LIST predicate expects exactly one argument");
        }
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar el argumento y comprobar si es una lista
        Object argument = arguments.get(0).evaluate(environment);
        return argument instanceof List;
    }
}
