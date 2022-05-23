package common;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void readFileAsString() throws IOException, URISyntaxException {
        assertEquals("199\n" +
                "200\n" +
                "208\n" +
                "210\n" +
                "200\n" +
                "207\n" +
                "240\n" +
                "269\n" +
                "260\n" +
                "263\n", Util.readFileAsString("day01/test.txt"));
    }

    @Test
    public void stringStream() {
        String input = "199\n" +
                "200\n" +
                "208\n";

        assertArrayEquals(Util.asStringStream(input).toArray(), new String[] {"199", "200", "208"});
    }
    @Test
    public void asInt() {
        String input = "199\n" +
                "200\n" +
                "208\n";

        assertArrayEquals(Util.asStringStream(input).mapToInt(Util::asInteger).toArray(), new int[] {199, 200, 208});
    }

}