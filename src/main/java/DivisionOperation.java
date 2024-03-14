import java.util.List;

public class DivisionOperation implements BuiltInOperation {
    @Override
    public Object execute(List<Object> arguments) {
        if (arguments.size() < 2) {
            throw new IllegalArgumentException("La operación de división requiere al menos dos argumentos");
        }

        double result = ((Number) arguments.get(0)).doubleValue();
        for (int i = 1; i < arguments.size(); i++) {
            Object arg = arguments.get(i);
            if (arg instanceof Number) {
                double value = ((Number) arg).doubleValue();
                if (value == 0) {
                    throw new ArithmeticException("División por cero");
                }
                result /= value;
            } else {
                throw new IllegalArgumentException("El argumento no es un número: " + arg);
            }
        }
        return result;
    }
}
