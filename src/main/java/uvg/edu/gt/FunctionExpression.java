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
                Environment localEnv = new Environment(environment);
                // Asignar los valores de los argumentos a los parámetros locales
                for (int i = 0; i < arguments.size(); i++) {

                    String parameter = function.getParameters().get(i);
                    System.out.println("Parametro" + parameter);
                    System.out.println("n" + localEnv.getVariables());
                    Object argumentoEvaluado = arguments.get(i).evaluate(localEnv);
                    localEnv.defineVariable(parameter, new ConstantExpression(argumentoEvaluado));

                    
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