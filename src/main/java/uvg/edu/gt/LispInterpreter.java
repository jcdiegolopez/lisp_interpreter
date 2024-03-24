package uvg.edu.gt;

import java.util.List;

public class LispInterpreter {
    public static void main(String[] args) {
        // Definir el código Lisp a evaluar
        String inputProgram = "(defun sum(a b) (+ a b))(sum 5 3)";

        // Paso 1: Tokenización
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize(inputProgram);
        System.err.println("tokens: " + tokens);

        // Paso 2: Parseo
        Environment environment = new Environment();
        Parser parser = new Parser(tokens, environment);
        List<Expression> expressions = parser.parse();
        

        // Paso 3: Evaluación
        
        int count = 1;
        for (Expression exp : expressions) {
            System.out.println(exp);
            System.out.println("Resultado Exp "+ count + " :" +exp.evaluate(environment));
            count++;
        }
    }
}
