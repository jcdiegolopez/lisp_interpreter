package uvg.edu.gt;

import java.util.List;

public class FunctionExpression extends Expression {
    private String functionName;

    public FunctionExpression(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public Object evaluate(Environment environment) {
        return environment.lookupFunction(this.functionName);
    }

    @Override
    public String toString() {
        return functionName; 
    }
}