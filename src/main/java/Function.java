import java.util.List;

public class Function {
    private String name;
    private List<Parameter> parameters;
    private Expression body;

    public Function(String name, List<Parameter> parameters, Expression body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    public Object call(Environment environment, List<Object> arguments) {
        // Crear un nuevo entorno para la llamada de la función
        Environment functionEnv = new Environment();

        // Asignar los valores de los parámetros en el nuevo entorno
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);
            Object argument = arguments.get(i);
            functionEnv.addVariable(parameter.getName(), argument);

        }

        // Evaluar el cuerpo de la función en el nuevo entorno y devolver el resultado

        return body.evaluate(functionEnv);
    }

    // Otros métodos según sea necesario
}
