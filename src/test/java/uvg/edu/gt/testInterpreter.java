package uvg.edu.gt;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class testInterpreter {
    @Test
    public void testInterpretert() {
        // Crear un Lexer y obtener los tokens de una expresión aritmética
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize("(defun factorial (n)(cond ((<= n 1) 1)(t (* n (factorial (- n 1))))))(factorial 6)");

        // Crear un Parser y parsear los tokens
        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();

        // Verificar que se haya generado una expresión y que sea de tipo ArithmeticExpression
        assertEquals(1, expressions.size());
        assertTrue(expressions.get(0) instanceof Expression);

        // Verificar que la expresión sea evaluada correctamente
        Environment environment = new Environment();
        assertEquals(720, expressions.get(0).evaluate(environment));
    }
}
