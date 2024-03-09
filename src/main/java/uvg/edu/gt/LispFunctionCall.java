package uvg.edu.gt;

import java.util.List;
import java.util.stream.Collectors;

public class LispFunctionCall implements LispExpression {
    private String functionName;
    private List<LispExpression> arguments;

    public LispFunctionCall(String functionName, List<LispExpression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<LispExpression> getArguments() {
        return arguments;
    }

    @Override
    public LispExpression evaluate(Environment environment) {
        // Evaluamos cada argumento en el entorno dado.
        List<LispExpression> evaluatedArguments = arguments.stream()
                .map(arg -> arg.evaluate(environment))
                .collect(Collectors.toList());

        // Obtenemos la función del entorno.
        LispFunction function = environment.getFunction(functionName);
        if (function == null) {
            throw new RuntimeException("Error: La función '" + functionName + "' no está definida");
        }

        // Aplicamos la función a los argumentos evaluados.
        return function.apply(evaluatedArguments, environment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(functionName);
        for (LispExpression arg : arguments) {
            sb.append(" ").append(arg.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}
