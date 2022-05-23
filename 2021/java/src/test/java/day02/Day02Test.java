package day02;

import common.Coordinate;
import common.Util;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class Day02Test {

    @Test
    public void asCoordinate() {
        Day02 day02 = new Day02();
        assertEquals(new Coordinate(2, 0), day02.asCoordinate("forward 2"));
    }

    @Test
    public void product() {
        assertEquals(Day02.product(new Coordinate(2, 7)), 14);
    }

    @Test
    public void pt1test() throws IOException, URISyntaxException {
        String puzzleInput = Util.readFileAsString("day02/test.txt");
        Day02 day02 = new Day02();
        assertEquals(150, day02.pt1(puzzleInput));
    }
    @Test
    public void pt1() throws IOException, URISyntaxException {
        String puzzleInput = Util.readFileAsString("day02/input.txt");
        Day02 day02 = new Day02();
        assertEquals(1938402, day02.pt1(puzzleInput));
    }

    @Test
    public void pt2test() throws IOException, URISyntaxException{
        String puzzleInput = Util.readFileAsString("day02/test.txt");
        Day02 day02 = new Day02();
        assertEquals(900, day02.pt2(puzzleInput));
    }
    @Test
    public void pt2() throws IOException, URISyntaxException{
        String puzzleInput = Util.readFileAsString("day02/input.txt");
        Day02 day02 = new Day02();
        assertEquals(1947878632, day02.pt2(puzzleInput));
    }
}