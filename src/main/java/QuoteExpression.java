import java.util.List;

public class QuoteExpression extends Expression {
    private ListN expression;

    public QuoteExpression(ListN expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Devuelve la expresión sin evaluar
        return expression.getElements();
    }
}
