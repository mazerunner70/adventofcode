package day03;

import common.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day03 {

    public static Map<Integer, Integer> bitFrequency(Map<Integer, Integer> bitFrequency, String bits) {
        Map<Integer, Integer> newFrequencies = new HashMap<>();
        newFrequencies.put(-1, bitFrequency.getOrDefault(-1, 0)+1);
        for (int i = 0; i<bits.length(); ++i) {
            newFrequencies.put(i, bitFrequency.getOrDefault(i, 0) + (bits.charAt(i) == '1' ? 1 : 0));
        }
        return newFrequencies;
    }

    public static  Map<Integer, Integer> combiner ( Map<Integer, Integer> a,  Map<Integer, Integer> b) {
        return a;
    }

    public static List<Integer> arraySet(List<Integer> arr, int index, int value) {
        if (index >= arr.size()) {
            arr.addAll(Collections.nCopies(arr.size()-index+1, Integer.valueOf(0)));
        }
        arr.set(index, value);
        return arr;
    }

    public static List<Integer> combine2(List<Integer> a, List<Integer> b) {
        return a;
    }


    public String mostCommon1(Map<Integer, Integer> freq) {
        int  count = freq.get(-1);
        List<Integer> initList = new ArrayList<>();
        List<Integer> commons =freq.entrySet()
                .stream()
                .filter(x->x.getKey()>=0)
                .reduce(initList,
                        (acc, el)->Day03.arraySet(acc,el.getKey(), el.getValue()), Day03::combine2);
        List<String> list = commons.stream().map(x->Integer.toString(x > count/2?1:0)).collect(Collectors.toList());
        return list.stream().collect(Collectors.joining());
    }

    public String OnesComplement(String binaryString) {
        return binaryString
                .replaceAll("1", "T")
                .replaceAll("0", "1")
                .replaceAll("T", "0");
    }

    public int pt1(String puzzleInput) {
        Map<Integer, Integer> frwq = Util.asStringStream(puzzleInput)
                .reduce(new HashMap<Integer, Integer>(), Day03::bitFrequency, Day03::combiner);
        String result = mostCommon1(frwq);
        return Integer.parseInt(result, 2) * Integer.parseInt(OnesComplement(result), 2);
    }

}


//1101 = 13