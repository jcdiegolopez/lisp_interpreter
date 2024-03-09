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
        System.out.println(program);

        // Paso 3: Crear un entorno y evaluar el programa
        Environment environment = new Environment();
        Evaluator evaluator = new Evaluator();
        LispExpression result = evaluator.evaluate(program, environment);

        // Imprimir el resultado
        System.out.println(result);
    }
}