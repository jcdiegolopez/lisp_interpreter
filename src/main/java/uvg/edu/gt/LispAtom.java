package uvg.edu.gt;

public class LispAtom implements LispExpression {
    private String value;
    private boolean isNumber;
    private boolean isString;
    private boolean isBoolean;

    public LispAtom(String value) {
        this.value = value;
        analyzeType();
    }

    public String getValue() {
        return value;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isString() {
        return isString;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    private void analyzeType() {
        // Verificar si el valor del átomo es un número.
        try {
            Double.parseDouble(value);
            isNumber = true;
            return;
        } catch (NumberFormatException e) {
            // No es un número.
            isNumber = false;
        }

        // Verificar si el valor del átomo es una cadena.
        if (value.startsWith("\"") && value.endsWith("\"")) {
            isString = true;
            return;
        } else {
            isString = false;
        }

        // Verificar si el valor del átomo es un booleano.
        if (value.equals("t") || value.equals("true") || value.equals("nil") || value.equals("false")) {
            isBoolean = true;
        } else {
            isBoolean = false;
        }
    }

    @Override
    public LispExpression evaluate(Environment environment) {
        // La evaluación de un átomo simplemente devuelve el átomo mismo.
        return this;
    }

    @Override
    public String toString() {
        return value;
    }
}
