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
        
        return null;
    }

    public List<String> getParameters() {
        return parameters;
    }
    public List<Expression> getBody() {
        return body;
    }
}
