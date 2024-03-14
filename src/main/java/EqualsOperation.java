import java.util.List;

public class EqualsOperation implements BuiltInOperation {
    @Override
    public Object execute(List<Object> arguments) {
        if (arguments.size() < 2) {
            throw new IllegalArgumentException("La operación de igualdad requiere al menos dos argumentos");
        }

        Object firstValue = arguments.get(0);
        for (int i = 1; i < arguments.size(); i++) {
            if (!firstValue.equals(arguments.get(i))) {
                return false;
            }
        }
        return true;
    }
}
