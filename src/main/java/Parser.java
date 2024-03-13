import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    public List<Expression> parse(String code) {
        List<Token> tokens = tokenize(code);
        return parseExpressions(tokens);
    }

    private List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : code.toCharArray()) {
            if (Character.isWhitespace(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(new Token(currentToken.toString()));
                    currentToken.setLength(0);
                }
            } else if (c == '(' || c == ')') {
                if (currentToken.length() > 0) {
                    tokens.add(new Token(currentToken.toString()));
                    currentToken.setLength(0);
                }
                tokens.add(new Token(String.valueOf(c)));
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(new Token(currentToken.toString()));
        }

        return tokens;
    }

    private List<Expression> parseExpressions(List<Token> tokens) {
        List<Expression> expressions = new ArrayList<>();
        Stack<List<Expression>> stack = new Stack<>();
        stack.push(expressions);

        for (Token token : tokens) {
            if (token.getValue().equals("(")) {
                List<Expression> sublist = new ArrayList<>();
                stack.peek().add(new ListN(sublist));
                stack.push(sublist);
            } else if (token.getValue().equals(")")) {
                stack.pop();
            } else if (token.getValue().equals("'")) {
                stack.peek().add(new QuoteExpression(stack.peek().remove(stack.peek().size() - 1)));
            } else if (token.getValue().equalsIgnoreCase("defun")) {
                parseDefun(stack.peek(), tokens);
            } else {
                stack.peek().add(parseToken(token));
            }
        }

        return expressions;
    }

    private void parseDefun(List<Expression> expressions, List<Token> tokens) {
        // Buscar el nombre de la función
        String functionName = getNextToken(tokens);

        // Buscar la lista de parámetros
        List<Parameter> parameters = parseParameters(tokens);

        // Buscar el cuerpo de la función
        List<Expression> body = new ArrayList<>();
        while (!tokens.isEmpty() && !tokens.get(0).getValue().equals(")")) {
            body.addAll(parseExpressions(tokens));
        }

        // Agregar la función al entorno actual
        expressions.add(new DefunExpression(functionName, parameters, body));
    }

    private List<Parameter> parseParameters(List<Token> tokens) {
        List<Parameter> parameters = new ArrayList<>();
        String token = getNextToken(tokens);
        while (!token.equals(")")) {
            parameters.add(new Parameter(token));
            token = getNextToken(tokens);
        }
        return parameters;
    }

    private String getNextToken(List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Unexpected end of input");
        }
        return tokens.remove(0).getValue();
    }

    private Expression parseToken(Token token) {
        try {
            double value = Double.parseDouble(token.getValue());
            return new Atom(value);
        } catch (NumberFormatException e) {
            // Si no es un número, es un símbolo
            return new Symbol(token.getValue());
        }
    }
}

class Token {
    private String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
