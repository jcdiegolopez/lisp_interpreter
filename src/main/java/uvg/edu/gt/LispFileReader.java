package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The `LispFileReader` class provides a method to read the contents of a Lisp file given its file
 * path.
 */
public class LispFileReader {
    /**
     * The function `readLispFile` reads the contents of a file specified by the `filePath` parameter
     * and returns it as a single string.
     * 
     * @param filePath The `filePath` parameter in the `readLispFile` method is a string that
     * represents the path to the Lisp file that you want to read. This method reads the content of the
     * specified Lisp file and returns it as a single string.
     * @return The `readLispFile` method reads the content of a file specified by the `filePath`
     * parameter and returns the content as a single string.
     */
    public static String readLispFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }
}
