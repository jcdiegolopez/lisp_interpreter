package uvg.edu.gt;

import java.util.List;

public class LispInterpreter {
    public static void main(String[] args) {
        String inputProgram = "(defun sum (a b) (+ a b))";

        // Paso 1: Tokenización
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize(inputProgram);
        System.out.println(tokens);

        // Paso 2: Análisis Sintáctico
        Parser parser = new Parser();
        LispExpression program = parser.parse(tokens);

        // Paso 3: Crear un entorno y evaluar el programa
        Environment environment = new Environment();

        // Definir la función suma en el entorno
        environment.setFunction("sum", new LispFunction(List.of("a", "b"), program, environment));


        // Evaluar la llamada a función (sum 3 4)
        Evaluator evaluator = new Evaluator();
        LispExpression result = evaluator.evaluate(new LispFunctionCall("sum", List.of(new LispAtom("3"), new LispAtom("4"))), environment);

        // Imprimir el resultado
        System.out.println(result);
    }
}
