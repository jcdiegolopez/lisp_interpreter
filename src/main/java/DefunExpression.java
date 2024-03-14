import java.util.List;

public class DefunExpression extends Expression {
    private String functionName;
    private List<Parameter> parameters;
    private List<Expression> body;

    public DefunExpression(String functionName, List<Parameter> parameters, List<Expression> body) {
        this.functionName = functionName;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Crear una nueva función y agregarla al entorno
        Function function = new Function(functionName, parameters, (Expression) body);
        environment.addFunction(functionName, function);
        return null; // Devolvemos null ya que la definición de función no devuelve ningún valor
    }
}
