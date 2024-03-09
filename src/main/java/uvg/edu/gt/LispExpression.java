package uvg.edu.gt;

public interface LispExpression {
    // Método para evaluar la expresión en el contexto de un entorno dado.
    LispExpression evaluate(Environment environment);

    // Método para obtener una representación de cadena de la expresión Lisp.
    String toString();
}
