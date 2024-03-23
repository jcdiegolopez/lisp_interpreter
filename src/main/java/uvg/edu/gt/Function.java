package uvg.edu.gt;

import java.util.List;

public class Function extends Expression {
    private String name;
    private List<String> parameters;
    private List<Expression> body;

    public Function(String name, List<String> parameters, List<Expression> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Las funciones Lisp deben evaluarse en el contexto del entorno actual.
        // En esta implementación básica, solo se almacena la definición de la función en el entorno.
        // Para evaluar la función, debe buscarse en el entorno y luego ejecutar su cuerpo.
        environment.defineFunction(name, parameters, body);
        return null;
    }
}
