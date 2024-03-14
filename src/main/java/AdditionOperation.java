import java.util.List;

public class AdditionOperation implements BuiltInOperation {
    @Override
    public Object execute(List<Object> arguments) {
        double result = 0;
        for (Object arg : arguments) {
            if (arg instanceof Number) {
                result += ((Number) arg).doubleValue();
            } else {
                throw new IllegalArgumentException("Argument is not a number: " + arg);
            }
        }
        System.out.println(result);
        return result;
    }
}
