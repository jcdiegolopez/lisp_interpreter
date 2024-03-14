import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    private Environment environment;

    public Interpreter() {
        this.environment = new Environment();
        initializeBuiltInFunctions(); // Inicializar funciones integradas
    }

    private void initializeBuiltInFunctions() {
        // Agregar funciones integradas
        environment.addFunction("+", new BuiltInFunction("+", new AdditionOperation()));
        environment.addFunction("-", new BuiltInFunction("-", new SubtractionOperation()));
        environment.addFunction("*", new BuiltInFunction("*", new MultiplicationOperation()));
        environment.addFunction("/", new BuiltInFunction("/", new DivisionOperation()));
        environment.addFunction("=", new BuiltInFunction("=", new EqualsOperation()));
        environment.addFunction("<", new BuiltInFunction("<", new LessThanOperation()));
        environment.addFunction(">", new BuiltInFunction(">", new GreaterThanOperation()));

        // Agregar símbolos
/*      environment.addSymbol("defun");
        environment.addSymbol("setq");
         environment.addSymbol("quote");
        environment.addSymbol("cond");
        environment.addSymbol("atom");
        environment.addSymbol("list");
        environment.addSymbol("equal");*/
        // Agregar más símbolos según sea necesario
    }


    public Object interpret(String code) {
        // Parser para convertir el código Lisp en una estructura de datos
        Parser parser = new Parser();
        List<Expression>  expressions = parser.parse(code);

        // Evaluar cada expresión y devolver el resultado de la última
        Object result = null;
        for (Expression expression : expressions) {
            result = expression.evaluate(environment);
        }
        return result;
    }

    public void defineFunction(String functionName, List<Parameter> parameters, Expression body) {
        // Método para definir una nueva función en el entorno
        Function function = new Function(functionName, parameters, body);
        this.environment.addFunction(functionName, function);
    }

    public void setVariable(String variableName, Object value) {
        // Método para asignar un valor a una variable en el entorno
        this.environment.setVariable(variableName, value);
    }

    public static void main(String[] args) {
        // Instanciar el intérprete y ejecutar código Lisp
        Interpreter interpreter = new Interpreter();
        // Aquí puedes agregar el código Lisp para ser interpretado
        String code = "(setq a 3) (+ (* a 3) (/ 100 5))";
        // String code = "quote (+ 3 4)";
        Object result = interpreter.interpret(code);
        System.out.println("Resultado de la interpretación: " + result);
    }
}
