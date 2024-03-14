import java.util.List;
public abstract class Predicate extends Expression {
    protected List<Expression> arguments;

    public Predicate(List<Expression> arguments) {
        this.arguments = arguments;
    }
}
