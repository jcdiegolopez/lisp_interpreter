package uvg.edu.gt;

import java.util.List;

import java.io.IOException;

public class LispInterpreter {
    public static void main(String[] args) {
        try {
            // Leer el contenido del archivo Lisp
            String filePath = "archivo.lisp";
            String inputProgram = LispFileReader.readLispFile(filePath);

            // Resto del código sigue igual
            // Paso 1: Tokenización
            Lexer lexer = new Lexer();
            List<String> tokens = lexer.tokenize(inputProgram);
            System.err.println("tokens: " + tokens);

            // Paso 2: Parseo
            Environment environment = new Environment();
            Parser parser = new Parser(tokens);
            List<Expression> expressions = parser.parse();

            // Paso 3: Evaluación
            int count = 1;
            for (Expression exp : expressions) {
                System.out.println("Resultado Exp " + count + " :" + exp.evaluate(environment));
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo Lisp: " + e.getMessage());
        }
    }
}