package uvg.edu.gt;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

public class EnvironmentTest {

    @Test
    public void testDefineVariable() {
        Environment environment = new Environment();
        environment.defineVariable("x", new ConstantExpression(123));
        assertEquals(123, environment.lookupVariable("x").evaluate(environment));
    }

    @Test
    public void testDefineFunction() {
        Environment environment = new Environment();
        List<String> params = Arrays.asList("x", "y");
        List<Expression> body = Arrays.asList(new ArithmeticExpression("+", new VariableExpression("x"), new VariableExpression("y")));
        environment.defineFunction("add", params, body);
        Function function = environment.lookupFunction("add");
        assertNotNull(function);
        assertEquals(params, function.getParameters());
        assertEquals(body, function.getBody());
    }
}
