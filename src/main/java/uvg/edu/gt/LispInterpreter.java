package uvg.edu.gt;

import java.util.List;

public class LispInterpreter {
    public static void main(String[] args) {
        // Definir el código Lisp a evaluar
        String inputProgram = "(< (* 3 5) (3))";

        // Paso 1: Tokenización
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize(inputProgram);
        System.err.println(tokens);

        // Paso 2: Parseo
        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();
        

        // Paso 3: Evaluación
        Environment environment = new Environment();
        for (Expression exp : expressions) {
            System.err.println(exp);
            System.err.println(exp.evaluate(environment));
        }
    }
}
