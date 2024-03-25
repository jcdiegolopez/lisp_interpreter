package uvg.edu.gt;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.List;

public class ParserTest {

    @Test
    public void testParser() {
        // Crear un Lexer y obtener los tokens de una expresión aritmética
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize("(+ 1 2)");

        // Crear un Parser y parsear los tokens
        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();

        // Verificar que se haya generado una expresión y que sea de tipo ArithmeticExpression
        assertEquals(1, expressions.size());
        assertTrue(expressions.get(0) instanceof Expression);

        // Verificar que la expresión sea evaluada correctamente
        Environment environment = new Environment();
        assertEquals(3, expressions.get(0).evaluate(environment));
    }
}
