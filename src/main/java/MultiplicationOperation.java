import java.util.List;

public class MultiplicationOperation implements BuiltInOperation {
    @Override
    public Object execute(List<Object> arguments) {
        double result = 1; // Inicializamos en 1 para que no afecte la multiplicación
        for (Object arg : arguments) {
            if (arg instanceof Number) {
                result *= ((Number) arg).doubleValue();
            } else {
                throw new IllegalArgumentException("El argumento no es un número: " + arg);
            }
        }
        return result;
    }
}
