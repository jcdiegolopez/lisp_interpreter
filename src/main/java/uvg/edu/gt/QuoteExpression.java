package uvg.edu.gt;

public class QuoteExpression extends Expression {
    private String quotedString;

    public QuoteExpression(String quotedString) {
        this.quotedString = quotedString;
    }

    @Override
    public Object evaluate(Environment environment) {
        // En una implementación completa, podría devolver una representación adecuada del quoted string
        // Aquí simplemente devolveremos el string sin procesar
        return quotedString;
    }

    public String getQuotedString() {
        return quotedString;
    }

    @Override
    public String toString() {
        return "'" + quotedString;
    }
}
