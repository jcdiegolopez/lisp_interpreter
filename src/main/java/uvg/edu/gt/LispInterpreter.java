package uvg.edu.gt;

import java.util.List;

public class LispInterpreter {
    public static void main(String[] args) {
        String inputProgram = "(defun factorial (n) (if (<= n 1) 1 (* n (factorial (- n 1)))))";

        // Paso 1: Tokenización
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize(inputProgram);
        System.out.println(tokens);

        // Paso 2: Análisis Sintáctico
        Parser parser = new Parser();
        LispExpression program = parser.parse(tokens);

        // Paso 3: Crear un entorno y evaluar el programa
        Environment environment = new Environment();

        // Definir la función factorial en el entorno
        environment.setFunction("factorial", new LispFunction(List.of("n"), program, environment));


        // Evaluar la llamada a función (factorial 5)
        Evaluator evaluator = new Evaluator();
        LispExpression result = evaluator.evaluate(new LispFunctionCall("factorial", List.of(new LispAtom("5"))), environment);

        // Imprimir el resultado
        System.out.println(result);
    }
}
