package uvg.edu.gt;

/**
 * Clase que representa una expresión de cita en el lenguaje.
 * Extiende la clase Expression.
 */
public class QuoteExpression extends Expression {
    private String quotedString; // String citado

    /**
     * Constructor de la clase QuoteExpression.
     * @param quotedString String citado.
     */
    public QuoteExpression(String quotedString) {
        this.quotedString = quotedString;
    }

    /**
     * Evalúa la expresión de cita.
     * Devuelve el string citado sin procesar.
     * @param environment Entorno en el que se evalúa la expresión (no utilizado en este caso).
     * @return String citado sin procesar.
     */
    @Override
    public Object evaluate(Environment environment) {
        // En una implementación completa, podría devolver una representación adecuada del string citado
        // Aquí simplemente devolveremos el string sin procesar
        return quotedString;
    }

    /**
     * Devuelve el string citado.
     * @return String citado.
     */
    public String getQuotedString() {
        return quotedString;
    }

    /**
     * Devuelve una representación de cadena de la expresión de cita.
     * @return Representación de cadena de la expresión de cita.
     */
    @Override
    public String toString() {
        return "'" + quotedString; // Representación en forma de cadena de la expresión de cita
    }
}

