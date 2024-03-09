package uvg.edu.gt;

import java.util.List;
import java.util.ArrayList;

public class Parser {
    public LispExpression parse(List<String> tokens) {
        // Inicializar un índice para rastrear la posición actual en la lista de tokens.
        int index = 0;

        // Llamar al método privado parseExpression para comenzar el análisis sintáctico.
        return parseExpression(tokens, index);
    }

    private LispExpression parseExpression(List<String> tokens, int index) {
        // Verificar si hemos alcanzado el final de la lista de tokens.
        if (index >= tokens.size()) {
            // Si no hay más tokens, lanzar una excepción de sintaxis.
            throw new RuntimeException("Error de sintaxis: La expresión está incompleta");
        }

        // Obtener el token actual.
        String token = tokens.get(index);

        // Manejar diferentes tipos de expresiones Lisp.
        if (token.equals("(")) {
            // Si el token es un paréntesis de apertura, la expresión es una lista.
            return parseList(tokens, index);
        } else {
            // Si el token no es un paréntesis de apertura, se considera un átomo.
            return new LispAtom(token);
        }
    }

    private LispExpression parseList(List<String> tokens, int index) {
        // Avanzar al siguiente token después del paréntesis de apertura.
        index++;

        // Obtener el token actual.
        String functionName = tokens.get(index);

        // Avanzar al siguiente token después del nombre de la función.
        index++;

        // Si el token actual es "defun", interpretamos la lista como una definición de función.
        if (functionName.equals("defun")) {
            // Obtener el nombre de la función
            String defunName = tokens.get(index);

            // Avanzar al siguiente token después del nombre de la función
            index++;

            // Obtener la lista de parámetros
            List<String> parameters = new ArrayList<>();
            while (!tokens.get(index).equals("(")) {
                parameters.add(tokens.get(index));
                index++;
            }

            // Avanzar al siguiente token después del paréntesis de apertura de los argumentos
            index++;

            // Crear una lista para almacenar los argumentos de la función.
            List<LispExpression> arguments = new ArrayList<>();

            // Iterar sobre los tokens restantes hasta encontrar el paréntesis de cierre.
            while (!tokens.get(index).equals(")")) {
                // Llamar recursivamente a parseExpression para analizar cada argumento de la función.
                LispExpression argument = parseExpression(tokens, index);
                arguments.add(argument);

                // Incrementar el índice para pasar al siguiente token.
                index++;
            }

            // Devolver una expresión que represente la definición de la función.
            LispExpression body = parseExpression(tokens, index + 1);
            return new LispFunction(parameters, body, null);
        } else {
            // Si no es una definición de función, es una llamada a función normal.
            // Crear una lista para almacenar los argumentos de la función.
            List<LispExpression> arguments = new ArrayList<>();

            // Iterar sobre los tokens restantes hasta encontrar el paréntesis de cierre.
            while (!tokens.get(index).equals(")")) {
                // Llamar recursivamente a parseExpression para analizar cada argumento de la función.
                LispExpression argument = parseExpression(tokens, index);
                arguments.add(argument);

                // Incrementar el índice para pasar al siguiente token.
                index++;
            }

            // Devolver una expresión que represente la llamada a la función.
            return new LispFunctionCall(functionName, arguments);
        }
    }
}
