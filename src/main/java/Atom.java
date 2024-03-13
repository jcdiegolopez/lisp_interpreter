public class Atom extends Expression {
    private Object value;

    public Atom(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment environment) {
        // Devolver el valor del átomo
        return value;
    }
}
