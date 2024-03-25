package uvg.edu.gt;

import java.util.List;

/**
 * This class represents a function expression in Java.
 */
public class FunctionExpression extends Expression {
    private String functionName;
    private List<Expression> arguments;

    // The `public FunctionExpression(String functionName, List<Expression> arguments)` constructor in
    // the `FunctionExpression` class is initializing a new instance of the `FunctionExpression` class
    // with the provided `functionName` and a list of `arguments`.
    public FunctionExpression(String functionName, List<Expression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    /**
     * This Java function evaluates a function by matching its parameters with provided arguments in a
     * local environment.
     * 
     * @param environment The `environment` parameter in the `evaluate` method represents the current
     * environment in which the function is being evaluated. It is used to look up the function by
     * name, create a local environment for the function, define and evaluate the arguments within that
     * local environment, and finally evaluate the body of the function
     * @return The `evaluate` method is returning the result of evaluating the body of the function in
     * the local environment. The result is the output of the function after processing all the
     * expressions within its body.
     */
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

    /**
     * The `toString` method overrides the default behavior to return a string representation of the
     * function name and its arguments.
     * 
     * @return The `toString()` method is being overridden to return a string representation of the
     * `functionName` concatenated with the string representation of the `arguments` list.
     */
    @Override
    public String toString() {
        return functionName + arguments.toString();
    }
}