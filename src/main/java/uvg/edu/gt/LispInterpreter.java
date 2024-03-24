package uvg.edu.gt;

import java.util.List;

public class LispInterpreter {
    public static void main(String[] args) {
        // Definir el código Lisp a evaluar
        String inputProgram = "(COND ((EQUAL 3 2) 4) ((> 5 3) (- 7 5)))";

        // Paso 1: Tokenización
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize(inputProgram);
        System.err.println("tokens: " + tokens);

        // Paso 2: Parseo
        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();

        // Paso 3: Evaluación
        Environment environment = new Environment();
        int count = 1;
        for (Expression exp : expressions) {
            System.out.println("Resultado Exp " + count + " :" + exp.evaluate(environment));
            count++;
        }
    }
}