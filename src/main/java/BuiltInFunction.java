import java.util.List;
public class BuiltInFunction extends Function {
    private BuiltInOperation operation;

    public BuiltInFunction(String name, BuiltInOperation operation) {
        super(name, null, null);
        this.operation = operation;
    }

    @Override
    public Object call(Environment environment, List<Object> arguments) {
        // Llamar a la operación integrada y devolver el resultado
        return operation.execute(arguments);
    }
}
