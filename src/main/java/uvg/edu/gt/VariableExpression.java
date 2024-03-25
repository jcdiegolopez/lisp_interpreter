package uvg.edu.gt;

/**
 * Clase que representa una expresión que corresponde a una variable en el lenguaje.
 * Extiende la clase Expression.
 */
public class VariableExpression extends Expression {
    private String name; // Nombre de la variable

    /**
     * Constructor de la clase VariableExpression.
     * @param name Nombre de la variable.
     */
    public VariableExpression(String name) {
        this.name = name;
    }

    /**
     * Evalúa la expresión de la variable en el entorno dado.
     * Busca y devuelve el valor de la variable en el entorno.
     * @param environment Entorno en el que se evalúa la expresión.
     * @return Valor de la variable en el entorno.
     */
    @Override
    public Object evaluate(Environment environment) {
        return environment.lookupVariable(name).evaluate(environment);
    }

    /**
     * Devuelve una representación de cadena de la expresión de la variable.
     * @return Representación de cadena de la variable.
     */
    public String toString() {
        return name;
    }

    /**
     * Devuelve el nombre de la variable.
     * Este método está sin implementar y lanza una excepción de UnsupportedOperationException.
     * @return Nombre de la variable.
     */
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
}

