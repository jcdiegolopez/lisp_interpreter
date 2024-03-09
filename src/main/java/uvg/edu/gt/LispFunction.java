package uvg.edu.gt;

import java.util.List;

public class LispFunction implements LispExpression {
    private List<String> parameters;
    private LispExpression body;
    private Environment parentEnvironment;

    public LispFunction(List<String> parameters, LispExpression body, Environment parentEnvironment) {
        this.parameters = parameters;
        this.body = body;
        this.parentEnvironment = parentEnvironment;
    }


    public List<String> getParameters() {
        return parameters;
    }

    public LispExpression getBody() {
        return body;
    }

    public LispExpression apply(List<LispExpression> arguments, Environment environment) {
        // Creamos un nuevo entorno para la ejecución de la función, que extiende el entorno actual.
        Environment functionEnvironment = new Environment();

        // Copiamos las variables del entorno padre al nuevo entorno.
        for (String varName : parentEnvironment.variables.keySet()) {
            functionEnvironment.setVariable(varName, parentEnvironment.getVariable(varName));
        }

        // Asignamos los valores de los argumentos a los parámetros en el nuevo entorno.
        for (int i = 0; i < parameters.size(); i++) {
            functionEnvironment.setVariable(parameters.get(i), arguments.get(i));
        }

        // Evaluamos el cuerpo de la función en el nuevo entorno y devolvemos el resultado.
        return body.evaluate(functionEnvironment);
    }

    @Override
    public LispExpression evaluate(Environment environment) {
        return this; // La evaluación de una función devuelve la función misma.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(lambda (");
        for (String parameter : parameters) {
            sb.append(parameter).append(" ");
        }
        sb.append(") ");
        sb.append(body.toString());
        sb.append(")");
        return sb.toString();
    }
}
