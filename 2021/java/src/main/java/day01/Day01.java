package day01;

import common.Util;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day01 {

    public int[] diff (int[] intArray1, int[] intArray2) {
        return IntStream
                .range(0, Math.min(intArray1.length, intArray2.length))
                .map(i->intArray1[i] - intArray2[i]).toArray();
    }

    public int[] tail(int[] intArray) {
        return Arrays.copyOfRange(intArray, 1, intArray.length);
    }

    public int pt1(String puzzleInput) {
        int[] depths = Util.asStringStream(puzzleInput).mapToInt(Util::asInteger).toArray();
        int[] depthIncreases = Arrays.stream(diff(tail(depths), depths)).filter(i-> i>0).toArray();
        return depthIncreases.length;
    }

    public int pt2(String puzzleInput) {
        int[] depths = Util.asStringStream(puzzleInput).mapToInt(Util::asInteger).toArray();
        int[] depthIncreases = Arrays.stream(diff(tail(tail(tail(depths))), depths)).filter(i-> i>0).toArray();
        return depthIncreases.length;
    }



}
