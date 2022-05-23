package day01;

import common.Util;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class Day01v2Test {

    @Test
    public void pt1test() throws IOException, URISyntaxException {
        Day01v2 day01v2 = new Day01v2();
        String puzzleInput = Util.readFileAsString("day01/test.txt");
        assertEquals(7, day01v2.pt1and2(puzzleInput, 1));
    }
    @Test
    public void pt1() throws IOException, URISyntaxException {
        Day01v2 day01v2 = new Day01v2();
        String puzzleInput = Util.readFileAsString("day01/input.txt");
        assertEquals(1713, day01v2.pt1and2(puzzleInput, 1));
    }
    @Test
    public void pt2test() throws IOException, URISyntaxException {
        Day01v2 day01v2 = new Day01v2();
        String puzzleInput = Util.readFileAsString("day01/test.txt");
        assertEquals(5, day01v2.pt1and2(puzzleInput, 3));
    }
    @Test
    public void pt2() throws IOException, URISyntaxException {
        Day01v2 day01v2 = new Day01v2();
        String puzzleInput = Util.readFileAsString("day01/input.txt");
        assertEquals(1734, day01v2.pt1and2(puzzleInput, 3));
    }
}