package uvg.edu.gt;

import java.util.List;

public class FunctionExpression extends Expression {
    private String functionName;
    private List<Expression> arguments;

    public FunctionExpression(String functionName, List<Expression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public Object evaluate(Environment environment) {
        
        // Obtener la función del entorno
        Function function = environment.lookupFunction(this.functionName);
        if (function != null) {
            // Verificar si la cantidad de argumentos es la misma que la cantidad de parámetros de la función
            if (function.getParameters().size() == arguments.size()) {
                // Crear un nuevo entorno local para la función
                Environment localEnv = new Environment();
                // Asignar los valores de los argumentos a los parámetros locales
                for (int i = 0; i < arguments.size(); i++) {
                    String parameter = function.getParameters().get(i);
                    Object value = arguments.get(i).evaluate(environment);
                    localEnv.defineVariable(parameter, function);
                }
                // Evaluar el cuerpo de la función en el entorno local
                Object result = null;
                for (Expression exp : function.getBody()) {
                    result = exp.evaluate(localEnv);
                }
                return result;
                
            } else {
                throw new RuntimeException("Número incorrecto de argumentos para la función: " + functionName);
            }
        } else {
            throw new RuntimeException("Función no definida: " + functionName);
        }
    }

    @Override
    public String toString() {
        return functionName + arguments.toString();
    }
}