package uvg.edu.gt;

import java.util.List;

public class Evaluator {
    public LispExpression evaluate(LispExpression expression, Environment environment) {
        // Verificar el tipo de expresión y realizar la evaluación correspondiente.
        if (expression instanceof LispAtom) {
            // Si la expresión es un átomo, devolvemos su valor del entorno si está definido.
            LispAtom atom = (LispAtom) expression;
            String atomValue = atom.getValue();
            if (environment.variables.containsKey(atomValue)) {
                return environment.variables.get(atomValue);
            } else {
                return atom; // Si no está definido en el entorno, devolvemos el átomo tal cual.
            }
        } else if (expression instanceof LispFunctionCall) {
            // Si la expresión es una llamada a función, evaluamos la función en el entorno.
            LispFunctionCall functionCall = (LispFunctionCall) expression;
            String functionName = functionCall.getFunctionName();
            List<LispExpression> arguments = functionCall.getArguments();

            // Verificar si la función está definida en el entorno.
            if (!environment.functions.containsKey(functionName)) {
                throw new RuntimeException("Error: La función '" + functionName + "' no está definida");
            }

            // Obtener la función del entorno y aplicarla a los argumentos.
            LispFunction function = environment.functions.get(functionName);
            return function.apply(arguments, environment);
        } else {
            throw new RuntimeException("Error: Expresión no válida");
        }
    }
}
