package day03;

import common.Util;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class Day03Test {

    @Test
    public void pt1test() throws IOException, URISyntaxException {
        Day03 day03 = new Day03();
        String puzzleInput = Util.readFileAsString("day03/test.txt");
        assertEquals(198, day03.pt1(puzzleInput));
    }
    @Test
    public void pt1() throws IOException, URISyntaxException {
        Day03 day03 = new Day03();
        String puzzleInput = Util.readFileAsString("day03/input.txt");
        assertEquals(1025636, day03.pt1(puzzleInput));
    }
}