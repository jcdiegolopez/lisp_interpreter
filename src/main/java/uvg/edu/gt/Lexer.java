package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    // La expresión regular LISP_REGEX define patrones específicos para paréntesis, palabras, números, cadenas y operadores.
    

    // Este método toma una cadena de entrada y la divide en tokens.
    public List<String> tokenize(String input) {
        // Lista para almacenar los tokens resultantes.
        List<String> tokens = new ArrayList<>();
        
        // Dividir la entrada en palabras utilizando uno o más caracteres de espacio en blanco como delimitadores.
        String[] words = input.split("\\s+");

        // Iterar sobre cada palabra y agregar sus tokens al resultado.
        for (String word : words) {
            tokens.addAll(tokenizeWord(word));
        }

        // Devolver la lista de tokens.
        return tokens;
    }

    // Este método tokenizeWord toma una palabra y la divide en tokens.
    private List<String> tokenizeWord(String word) {
        // Lista para almacenar los tokens resultantes de la palabra.
        List<String> tokens = new ArrayList<>();

        // Patrón para identificar paréntesis, palabras, números y cadenas de texto.
        Pattern pattern = Pattern.compile("(<=|>=|=)|([()+\\-*/<>])|(\\w+)|(\\d+)|(\"[^\"]*\")|(')");



        // Crear un objeto Matcher para la palabra.
        Matcher matcher = pattern.matcher(word);

        // Iterar sobre las coincidencias en la palabra.
        while (matcher.find()) {
            // Iterar sobre los grupos de la coincidencia.
            for (int i = 1; i <= matcher.groupCount(); i++) {
                // Agregar el grupo no nulo a la lista de tokens.
                if (matcher.group(i) != null) {
                    tokens.add(matcher.group(i));
                }
            }
        }

        // Devolver la lista de tokens de la palabra.

        return tokens;
    }
}

