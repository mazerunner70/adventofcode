package day02;

import common.Coordinate;
import common.Util;

import java.util.Map;

import static java.lang.Math.abs;

public class Day02 {

    public static Map<String, Coordinate> commands = Map.ofEntries(
            Map.entry("forward", new Coordinate(1,0)),
            Map.entry("down", new Coordinate(0, 1)),
            Map.entry("up", new Coordinate(0,-1)) );

    public static Coordinate asCoordinate(String line) {
        String[] s = line.split(" ");
        int magnitude = Integer.parseInt(s[1]);
        return commands.get(s[0]).scaled(magnitude);
    }

     public static int product(Coordinate coordinate) {
        return coordinate.x * coordinate.y;
    }

    public int pt1(String puzzleInput) {
        Coordinate coordinate = Util.asStringStream(puzzleInput)
                .map(Day02::asCoordinate)
                .reduce(new Coordinate(0, 0), Coordinate::add);
        return product(coordinate);
    }

    public static AimedCoordinate asAimedCoordinate(String line) {
        return new AimedCoordinate(asCoordinate(line), 0);
    }

    public static class AimedCoordinate {
        public Coordinate coordinate;
        public int aim;
        public AimedCoordinate(Coordinate coordinate, int aim) {
            this.coordinate = coordinate;
            this.aim = aim;
        }
        public AimedCoordinate combine(AimedCoordinate other) {
            return new AimedCoordinate(
                    coordinate.add(new Coordinate(other.coordinate.x, aim * other.coordinate.x)),
                    aim + other.coordinate.y);
        }
    }

    public int pt2(String puzzleInput) {
        AimedCoordinate aimedCoordinate = Util.asStringStream(puzzleInput)
                .map(Day02::asAimedCoordinate)
                .reduce(new AimedCoordinate(new Coordinate(0, 0), 0), AimedCoordinate::combine);
        return product(aimedCoordinate.coordinate);
    }

}
