package common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Util {

    public static String readFileAsString(String filePath) throws IOException, URISyntaxException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(ClassLoader.getSystemResource(filePath).toURI())))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        return contentBuilder.toString();
    }

    public static Stream<String> asStringStream(String puzzleInput) {
        return Arrays.stream(puzzleInput.split("\n"));
    }

    public static int asInteger(String intString) {
        return Integer.parseInt(intString);
    }
}
