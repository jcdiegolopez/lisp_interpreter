package uvg.edu.gt;

/**
 * Clase que representa una expresión de asignación en el lenguaje.
 * Extiende la clase Expression.
 */
public class SetqExpression extends Expression {
    private String variable; // Variable a la que se asignará el valor
    private Expression value; // Valor que se asignará a la variable

    /**
     * Constructor de la clase SetqExpression.
     * @param variable Nombre de la variable a la que se asignará el valor.
     * @param value Expresión que representa el valor que se asignará a la variable.
     */
    public SetqExpression(String variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    /**
     * Evalúa la expresión de asignación en el entorno dado.
     * Evalúa la expresión de valor y asigna su resultado a la variable en el entorno.
     * @param environment Entorno en el que se evalúa la expresión.
     * @return Valor asignado a la variable.
     */
    @Override
    public Object evaluate(Environment environment) {
        Object evaluatedValue = value.evaluate(environment); // Evaluar la expresión de valor
        environment.defineVariable(variable, new ConstantExpression(evaluatedValue)); // Definir la variable en el entorno con el valor evaluado
        return evaluatedValue; // Devolver el valor asignado a la variable
    }

    /**
     * Devuelve una representación de cadena de la expresión de asignación.
     * @return Representación de cadena de la expresión de asignación.
     */
    public String toString() {
        return "(SETQ " + variable + " " + value.toString() + ")"; // Representación en forma de cadena de la expresión de asignación
    }
}

