package day01;

import common.Util;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day01v2 {

    public interface IntFunc { int exec(int x, int y);}

    public IntStream offsetPairMath(int[] array, int offset, IntFunc intFunc) {
        return IntStream
                .range(0, array.length-offset)
                .map(i->intFunc.exec(array[i], array[i+offset]));
    }

    public int pt1and2(String puzzleInput, int offset) {
//        int[] depths = Util.asIntegerList(puzzleInput);
//        int[] depthIncreases = offsetPairMath(depths, offset, (x, y)-> y-x).filter(i-> i>0).toArray();
//        return depthIncreases.length;
        return 0;
    }

}
