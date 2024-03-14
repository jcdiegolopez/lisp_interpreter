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
        System.out.println("tokens");
        for (Token token : tokens){
            System.out.println(token.getValue());
        }


        int index = 0; 
        while (index < tokens.size()) { //[setq, a, 3,)]
            Token token = tokens.get(index); 
            if (token.getValue().equals("(")) {
                List<Expression> sublist = new ArrayList<>();
                stack.peek().add(new ListN(sublist));
                stack.push(sublist);
            } else if (token.getValue().equals(")")) {
                stack.pop();
            } else if (token.getValue().equalsIgnoreCase("'") || token.getValue().equalsIgnoreCase("quote")) {
                // Verificar si hay un paréntesis de apertura "(" después de "quote"
                System.out.println("token 0 en quote");
                if (!tokens.isEmpty() && tokens.get(0).getValue().equals("(")) {
                    
                    List<Expression> sublist = (List<Expression>) getEnclosureValues(tokens,")");
                    stack.peek().add(new QuoteExpression( new ListN(sublist)));
                    System.out.println(sublist);
                    stack.push(sublist);
                } else {
                    // Manejar el error de falta de paréntesis de apertura después de "quote"
                    throw new RuntimeException("Error: Missing opening parenthesis after quote");
                }
            } else if (token.getValue().equalsIgnoreCase("defun")) {
                parseDefun(stack.peek(), tokens);
            } else if (token.getValue().equalsIgnoreCase("setq")) {
                // Lógica para manejar la expresión setq
                String variableName = getNextToken(tokens);
                System.out.println("variableName" + variableName);
                Expression valueExpression = parseToken(tokens.remove(0)); // Elimina el nombre de la variable
                stack.peek().add(new SetQExpression(variableName, valueExpression));
            } else if (token.getValue().equalsIgnoreCase("cond")) {
                // Lógica para manejar la expresión cond
                List<ConditionalExpression> conditions = parseCond(tokens);
                stack.peek().add(new CondExpression(conditions));
            } else if (token.getValue().equalsIgnoreCase("atom")) {
                // Lógica para manejar la función atom
                Expression expression = parseToken(tokens.remove(0)); // Elimina el token "atom"
                stack.peek().add(new AtomExpression(expression));
            } else if (token.getValue().equalsIgnoreCase("list")) {
                // Lógica para manejar la función list
                Expression expression = parseToken(tokens.remove(0)); // Elimina el token "list"
                stack.peek().add(new ListExpression(expression));
            } else if (token.getValue().equalsIgnoreCase("equal")) {
                // Lógica para manejar la función equal
                Expression expression1 = parseToken(tokens.remove(0)); // Elimina el token "equal"
                Expression expression2 = parseToken(tokens.remove(0)); // Elimina el primer argumento de equal
                stack.peek().add(new EqualExpression(expression1, expression2));
            } else {
                stack.peek().add(parseToken(token));
            }
            tokens.remove(0);

        }

        return expressions;
    }

    private Object parseQuote(List<Token> currentTokens){
        List<Expression> tokensParseados; 
        tokensParseados = getEnclosureTokens(currentTokens, "(", ")");
        return tokensParseados;

    }

    private Object getEnclosureValues(List<Token> currentTokens, String enclosure){
        for (Token token : currentTokens){
            System.out.println(token.getValue());
        }
        return null;
    }
    private List <Expression> getEnclosureTokens(List<Token> currentTokens, String opensure, String enclosure){
        List <Expression> tokensParseados;
        int index = 0; 
        while (index < currentTokens.size()){
            Token token = currentTokens.get(index); 
            if (token.getValue().equals(opensure)){
                getEnclosureTokens(currentTokens, opensure, enclosure);
            } else if (token.getValue().equals(enclosure)){
                return tokensParseados;
            } else if (token.getValue().equalsIgnoreCase("'") || token.getValue().equalsIgnoreCase("quote")) {
                // Verificar si hay un paréntesis de apertura "(" después de "quote"
                System.out.println("token 0 en quote");
                if (!currentTokens.isEmpty() && currentTokens.get(0).getValue().equals("(")) {
                    tokensParseados.add(new QuoteExpression( new ListN(getEnclosureTokens(currentTokens, "(", ")"))));
                } else {
                    // Manejar el error de falta de paréntesis de apertura después de "quote"
                    throw new RuntimeException("Error: Missing opening parenthesis after quote");
                }
            } else if (token.getValue().equalsIgnoreCase("defun")) {
                parseDefun(tokensParseados, currentTokens);
            } else if (token.getValue().equalsIgnoreCase("setq")) {
                // Lógica para manejar la expresión setq
                String variableName = getNextToken(currentTokens);
                System.out.println("variableName" + variableName);
                Expression valueExpression = parseToken(currentTokens.remove(0)); // Elimina el nombre de la variable
                tokensParseados.add(new SetQExpression(variableName, valueExpression));
            } else if (token.getValue().equalsIgnoreCase("cond")) {
                // Lógica para manejar la expresión cond
                List<ConditionalExpression> conditions = parseCond(currentTokens);
                tokensParseados.add(new CondExpression(conditions));
            } else if (token.getValue().equalsIgnoreCase("atom")) {
                // Lógica para manejar la función atom
                Expression expression = parseToken(currentTokens.remove(0)); // Elimina el token "atom"
                tokensParseados.add(new AtomExpression(expression));
            } else if (token.getValue().equalsIgnoreCase("list")) {
                // Lógica para manejar la función list
                Expression expression = parseToken(currentTokens.remove(0)); // Elimina el token "list"
                tokensParseados.add(new ListExpression(expression));
            } else if (token.getValue().equalsIgnoreCase("equal")) {
                // Lógica para manejar la función equal
                Expression expression1 = parseToken(currentTokens.remove(0)); // Elimina el token "equal"
                Expression expression2 = parseToken(currentTokens.remove(0)); // Elimina el primer argumento de equal
                tokensParseados.add(new EqualExpression(expression1, expression2));
            } else {
                tokensParseados.add(parseToken(token));
            }
            
        }
        return tokensParseados;
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

    private List<ConditionalExpression> parseCond(List<Token> tokens) {
        List<ConditionalExpression> conditions = new ArrayList<>();
        Token nextToken = tokens.get(0); // Obtener el primer token después de "cond"
        while (!nextToken.getValue().equals(")")) {
            Expression condition = parseToken(tokens.remove(0)); // Eliminar el token actual y convertirlo en una expresión
            Expression result = parseToken(tokens.remove(0)); // Eliminar el siguiente token y convertirlo en una expresión
            conditions.add(new ConditionalExpression(condition, result));
            nextToken = tokens.get(0); // Obtener el siguiente token después del resultado
        }
        return conditions;
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
        tokens.remove(0);
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
