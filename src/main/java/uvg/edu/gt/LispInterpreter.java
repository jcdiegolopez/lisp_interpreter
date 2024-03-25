package uvg.edu.gt;

import java.util.List;

import java.io.IOException;

public class LispInterpreter {
    /**
     * The main function reads a Lisp file, tokenizes its content, parses the tokens, evaluates the
     * expressions, and prints the results.
     */
    public static void main(String[] args) {
        
        try {
            // Leer el contenido del archivo Lisp
            String filePath = "archivo.lisp";
            String inputProgram = LispFileReader.readLispFile(filePath);

            // Resto del código sigue igual
            // Paso 1: Tokenización
            Lexer lexer = new Lexer();
            List<String> tokens = lexer.tokenize(inputProgram);

            // Paso 2: Parseo
            Environment environment = new Environment();
            Parser parser = new Parser(tokens);
            List<Expression> expressions = parser.parse();

            // Paso 3: Evaluación
            int count = 1;
            for (Expression exp : expressions) {
                exp.evaluate(environment);
                System.out.println("Resultado "+ count + ": " + exp.evaluate(environment));
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo Lisp: " + e.getMessage());
        }
    }
}