package day01;

import common.Util;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class Day01Test {

    @Test
    public void pt1test() throws IOException, URISyntaxException {
        Day01 day01 = new Day01();
        String puzzleInput = Util.readFileAsString("day01/test.txt");
        assertEquals(7, day01.pt1(puzzleInput));
    }
    @Test
    public void pt2test() throws IOException, URISyntaxException {
        Day01 day01 = new Day01();
        String puzzleInput = Util.readFileAsString("day01/test.txt");
        assertEquals(5, day01.pt2(puzzleInput));
    }

    @Test
    public void pt1() throws IOException, URISyntaxException {
        Day01 day01 = new Day01();
        String puzzleInput = Util.readFileAsString("day01/input.txt");
        assertEquals(1713, day01.pt1(puzzleInput));
    }
    @Test
    public void pt2() throws IOException, URISyntaxException {
        Day01 day01 = new Day01();
        String puzzleInput = Util.readFileAsString("day01/input.txt");
        assertEquals(1734, day01.pt2(puzzleInput));
    }



    @Test
    public void diff() {
        int[] array1 = {2, 3};
        int[] array2 = {4, 5};
        Day01 day01 = new Day01();
        assertArrayEquals(new int[] {-2, -2}, day01.diff(array1, array2));
    }


    @Test
    public void tail() {
        Day01 day01 = new Day01();
        assertArrayEquals(new int[] {2, 3}, day01.tail(new int[] {1, 2, 3}));
    }
}