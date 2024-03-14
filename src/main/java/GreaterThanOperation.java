import java.util.List;

public class GreaterThanOperation implements BuiltInOperation {
    @Override
    public Object execute(List<Object> arguments) {
        if (arguments.size() < 2) {
            throw new IllegalArgumentException("La operación de mayor que requiere al menos dos argumentos");
        }

        double previousValue = ((Number) arguments.get(0)).doubleValue();
        for (int i = 1; i < arguments.size(); i++) {
            double currentValue = ((Number) arguments.get(i)).doubleValue();
            if (previousValue <= currentValue) {
                return false;
            }
            previousValue = currentValue;
        }
        return true;
    }
}
