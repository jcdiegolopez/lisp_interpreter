import java.util.List;
public class LessThanPredicate extends Predicate {
    public LessThanPredicate(List<Expression> arguments) {
        super(arguments);
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("LESS THAN predicate expects exactly two arguments");
        }
    }

    @Override
    public Object evaluate(Environment environment) {
        // Evaluar los dos argumentos y comprobar si el primero es menor que el segundo
        double arg1 = ((Number) arguments.get(0).evaluate(environment)).doubleValue();
        double arg2 = ((Number) arguments.get(1).evaluate(environment)).doubleValue();
        return arg1 < arg2;
    }
}
