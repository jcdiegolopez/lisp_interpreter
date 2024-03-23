package uvg.edu.gt;


import java.util.ArrayList;
import java.util.List;

public class Parser {
    private int current = 0;
    private List<String> tokens;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<Expression> parse() {
        List<Expression> expressions = new ArrayList<>();
        while (current < tokens.size()) {
            expressions.add(parseExpression());
        }
        return expressions;
    }

    private Expression parseExpression() {
        String token = tokens.get(current++);
        if (isNumber(token)) {
            return new ConstantExpression(Integer.parseInt(token));
        } else if (token.equals("(")) {
            return parseList();
        } else if (token.equals("QUOTE") || token.equals("'")) {
            return parseQuote();
        } else if (token.equals("DEFUN")) {
            return parseDefun();
        } else if (token.equals("SETQ")) {
            return parseSetq();
        } else if (isPredicate(token)) {
            return parsePredicate(token);
        } else if (token.equals("COND")) {
            return parseCond();
        } else if (isArithmeticOperator(token)) {
            return parseArithmeticOperation(token);
        } else {
            return new VariableExpression(token);
        }
    }

    private ListExpression parseList() {
        List<Expression> expressions = new ArrayList<>();
        while (!tokens.get(current).equals(")")) {
            System.err.println("Parsing list element: " + tokens.get(current));
            expressions.add(parseExpression());
        }
        current++;
        return new ListExpression(expressions);
    }

    private Expression parseQuote() {
        // Para la instrucción QUOTE, simplemente devuelve la expresión siguiente.
        return parseExpression();
    }



    private Expression parseArithmeticOperation(String operator) {
        Expression left = parseExpression();
        Expression right = parseExpression();
        return new ArithmeticExpression(operator, left, right);
    }

    private boolean isArithmeticOperator(String token) {
        
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");

    }
    
    

    private Expression parseDefun() {
        // Parsea la definición de una función.
        String functionName = tokens.get(current++);
        List<String> parameters = parseParameterList();
        List<Expression> body = new ArrayList<>();
        while (!tokens.get(current).equals(")")) {
            body.add(parseExpression());
        }
        current++; // Consumir el token ')'
        return new DefunExpression(functionName, parameters, body);
    }

    private List<String> parseParameterList() {
        List<String> parameters = new ArrayList<>();
        current++; // Consumir el token '('
        while (!tokens.get(current).equals(")")) {
            parameters.add(tokens.get(current++));
        }
        current++; // Consumir el token ')'
        return parameters;
    }

    private Expression parseSetq() {
        // Parsea una expresión SETQ.
        String variable = tokens.get(current++);
        Expression value = parseExpression();
        return new SetqExpression(variable, value);
    }

    private Expression parsePredicate(String token) {
        // Parsea un predicado.
        List<Expression> arguments = new ArrayList<>();
        if (token.equals("ATOM") || token.equals("LIST")) {
            if (tokens.get(current).equals("(")) {
                // Si es un predicado que espera una lista de argumentos
                current++; // Consumir el token '('
                while (!tokens.get(current).equals(")")) {
                    arguments.add(parseExpression());
                }
                current++; // Consumir el token ')'
            } else {
                // Si el predicado espera un solo argumento
                arguments.add(parseExpression());
            }
        } else if (token.equals("EQUAL")) {
            // Predicado EQUAL espera dos argumentos
            arguments.add(parseExpression());
            arguments.add(parseExpression());
        } else if (token.equals("<") || token.equals(">")) {
            // Predicados de comparación (<, >) esperan dos argumentos
            arguments.add(parseExpression());
            arguments.add(parseExpression());
        } else {
            // Otros predicados que no necesitan verificación específica de argumentos
            while (!tokens.get(current).equals(")")) {
                arguments.add(parseExpression());
            }
            current++; // Consumir el token ')'
        }
        return new PredicateExpression(token, arguments);
    }
    

    private Expression parseCond() {
        // Parsea una expresión COND.
        List<ConditionalExpression.Branch> branches = new ArrayList<>();
        current++; // Consumir el token '('
        while (!tokens.get(current).equals(")")) {
            Expression condition = parseExpression();
            Expression result = parseExpression();
            branches.add(new ConditionalExpression.Branch(condition, result));
        }
        current++; // Consumir el token ')'
        return new ConditionalExpression(branches);
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isPredicate(String token) {
        return token.equals("ATOM") || token.equals("LIST") || token.equals("EQUAL") ||
               token.equals("<") || token.equals(">");
    }
}
