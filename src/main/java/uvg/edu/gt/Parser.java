package uvg.edu.gt;


import java.util.ArrayList;
import java.util.List;

public class Parser {
    private int current = 0;
    private List<String> tokens;
    private List<String> functionNames = new ArrayList<>();

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
        } else if (token.equalsIgnoreCase("(")) {
            return parseList();
        } else if (token.equalsIgnoreCase("QUOTE") || token.equals("'")) {
            return parseQuote();
        } else if (token.equalsIgnoreCase("DEFUN")) {
            return parseDefun();
        } else if (token.equalsIgnoreCase("SETQ")) {
            return parseSetq();
        } else if (isPredicate(token)) {
            return parsePredicate(token);
        } else if (token.equalsIgnoreCase("COND")) {
            return parseCond();
        } else if (isArithmeticOperator(token)) {
            System.out.println(token);
            return parseArithmeticOperation(token);
        } else if(functionNames.contains(token)){
            List<Expression> arguments = new ArrayList<>();
            while (!tokens.get(current).equals(")")) {
                arguments.add(parseExpression());
            }
            
            return new FunctionExpression(token, arguments);
        }else{
            return new VariableExpression(token);
        }
    }

    private ListExpression parseList() {
        List<Expression> expressions = new ArrayList<>();
        while (!tokens.get(current).equals(")")) {
            expressions.add(parseExpression());
        }
        current++;
        return new ListExpression(expressions);
    }

    private Expression parseQuote() {
        StringBuilder quotedStringBuilder = new StringBuilder();
        int openParenthesisCount = 0; // Contador para el número de paréntesis abiertos
    
        // Iterar sobre los tokens hasta encontrar el paréntesis de cierre correspondiente
        while (current < tokens.size()) {
            String currentToken = tokens.get(current);
            if (currentToken.equals("(")) {
                openParenthesisCount++; // Incrementar el contador de paréntesis abiertos
            } else if (currentToken.equals(")")) {
                if (openParenthesisCount == 0) {
                    break; // Salir si ya se ha encontrado el paréntesis de cierre correspondiente
                }
                openParenthesisCount--; // Decrementar el contador de paréntesis abiertos
            }
    
            // Agregar el token actual al string quoted
            quotedStringBuilder.append(currentToken).append(" ");
            current++;
        }
    
        // Devolver la expresión Quote con el string quoted generado
        return new QuoteExpression(quotedStringBuilder.toString().trim());
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
        String functionName = tokens.get(current++);
        functionNames.add(functionName);
        List<String> parameters = parseParameterList();
        List<Expression> body = new ArrayList<>();
        
        while (!tokens.get(current).equals(")")) {
            body.add(parseExpression());
            
        }
        
        
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
    

    /*private Expression parseCond() {
        // Parsea una expresión COND.
        System.out.println("entrando COND");
        List<ConditionalExpression.Branch> branches = new ArrayList<>();
        current++; // Consumir el token '('
        while (!tokens.get(current).equals(")") || tokens.get(current).equals("(")) {
            Expression condition = parseExpression();
            Expression result = parseExpression();
            System.out.println(condition);
            System.out.println("Result");
            System.out.println(result);
            branches.add(new ConditionalExpression.Branch(condition, result));
            current++; // Consumir el token ')'
            if (tokens.size() == current){
                break;
            }
        }
        return new ConditionalExpression(branches);
    }*/
    private Expression parseCond() {
        System.out.println("entrando COND");
        List<ConditionalExpression.Branch> branches = new ArrayList<>();
        current++; // Consumir el token '('
        while (!tokens.get(current).equals(")")) {
            current++; // Consumir el token '(' de la condición
            Expression condition = parseExpression();
            current++; // Consumir el token '(' del resultado
            Expression result = parseExpression();
            branches.add(new ConditionalExpression.Branch(condition, result));
            System.out.println("agreagr");
            System.out.println(condition);
            System.out.println(result);
            current++; // Consumir el token ')'
            current++; // Consumir el token ')'

            if (tokens.size() == current){
                current--;
                break;
            }
        }
        System.out.println(branches);
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
