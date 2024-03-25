package uvg.edu.gt;

import java.util.List;

/**
 * The `Function` class represents a function with a name, parameters, and body in Java.
 */

public class Function extends Expression {
    private String name;
    private List<String> parameters;
    private List<Expression> body;

    public Function(String name, List<String> parameters, List<Expression> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    // The code snippet you provided is from a Java class named `Function`. Here's what each part does:
    @Override
    public Object evaluate(Environment environment) {
        
        return null;
    }

    public List<String> getParameters() {
        return parameters;
    }
    public List<Expression> getBody() {
        return body;
    }
}
