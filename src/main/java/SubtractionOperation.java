import java.util.List;

public class SubtractionOperation implements BuiltInOperation {
    @Override
    public Object execute(List<Object> arguments) {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("Subtraction operation requires at least one argument");
        }

        double result = ((Number) arguments.get(0)).doubleValue();
        for (int i = 1; i < arguments.size(); i++) {
            Object arg = arguments.get(i);
            if (arg instanceof Number) {
                result -= ((Number) arg).doubleValue();
            } else {
                throw new IllegalArgumentException("Argument is not a number: " + arg);
            }
        }
        return result;
    }
}
