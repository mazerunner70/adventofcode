package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day08Test extends AnyFlatSpec {

  behavior of "Day08Test"

  it should "pt1" in {
    val testLines = loadList("days/day08/test.txt")
    val day08 = new Day08()
    assert(day08.pt1(testLines) == 26)
    val lines = loadList("days/day08/input.txt")
    assert(day08.pt1(lines) == 321)
  }
//  it should "pt2-old" in {
//    val day08 = new Day08()
//    assert (day08.segments2digits == Map('4' -> List(8, 2, 6, 0), '5' -> List(4, 8, 3, 7, 9, 6, 5, 0),
//      '0' -> List(8, 3, 7, 2, 9, 6, 5, 0), '2' -> List(4, 8, 3, 7, 2, 9, 1, 0), '3' -> List(4, 8, 3, 2, 9, 6, 5),
//      '6' -> List(8, 3, 2, 9, 6, 1, 5, 0), '1' -> List(4, 8, 9, 6, 5, 0)))
//    assert(day08.segmentFingerprints == Map(List(0, 4, 5, 6, 8, 9) -> '1', List(0, 2, 6, 8) -> '4',
//      List(0, 2, 3, 5, 6, 7, 8, 9) -> '0', List(0, 1, 2, 3, 5, 6, 8, 9) -> '6', List(0, 3, 4, 5, 6, 7, 8, 9) -> '5',
//      List(2, 3, 4, 5, 6, 8, 9) -> '3', List(0, 1, 2, 3, 4, 7, 8, 9) -> '2'))
//    val sampleList = List("acedgfb", "cdfbe", "gcdfa", "fbcad", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab")
//    assert(day08.allowedPermutations(sampleList).size == 36)
//    assert(day08.allowedPermutations(sampleList.sortBy(_.length)).slice(0,36) == 48)
//    assert(day08.fingerprint(sampleList)==
//      Map('e' -> List(2, 3, 6, 7, 8, 9), 'f' -> List(2, 3, 4, 5, 6, 7, 9), 'a' -> List(0, 1, 2, 4, 5, 6, 8, 9), 'b' -> List(0, 1, 2, 3, 5, 6, 7, 8, 9), 'g' -> List(4, 7, 8, 9), 'c' -> List(3, 4, 5, 6, 7, 8, 9), 'd' -> List(1, 3, 4, 5, 6, 7, 8, 9)))
//    assert(day08.findMappings(sampleList) ==
//      Map('e' -> None, 'f' -> None, 'a' -> None, 'b' -> None, 'g' -> None, 'c' -> None, 'd' -> None))
//    assert(day08.findMatchingFingerprint(sampleList) == List())
//    val testLines = loadList("days/day08/test.txt")
//    assert(day08.pt2(testLines) == Map('e' -> List(0, 1, 5, 6, 7, 8), 'f' -> List(0, 1, 2, 3, 5, 6, 7),
//      'a' -> List(0, 2, 3, 4, 5, 7, 8, 9), 'b' -> List(0, 1, 3, 4, 5, 6, 7, 8, 9), 'g' -> List(0, 2, 6, 8),
//      'c' -> List(0, 1, 2, 3, 5, 6, 8), 'd' -> List(0, 1, 2, 3, 4, 5, 6, 8)))
//  }
//6-g,
  it should "pt2" in {
    val day08 = new Day08()
    val sampleList = List("acedgfb", "cdfbe", "gcdfa", "fbcad", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab")
    assert(day08.remap("acedgfb", "abcdefg") == "abcdefg")
    assert(day08.remap("acedgfb", "defgabc") == "abcdefg")
    assert(day08.remap("ab", "cfgabde") == "cf")
    assert(day08.remap("cdfbe", "cfgabde") == "abdfg")
    assert(day08.permutations.size == 5040)
    assert(day08.checkRemap("abcdefg") == true)
    assert(day08.checkMapping(sampleList, "cfgabde"))
    assert(day08.permutations.find(x=>x == "abcdegf") == Some("abcdegf"))
    assert(day08.permutations.find(x=>x == "cfgabde") == Some("cfgabde"))
    assert(day08.determineMapping(sampleList) == "cfgabde")
    assert(day08.remap("cdfeb", "cfgabde") == "abdfg")
    assert(day08.asDigit("cdfeb", "cfgabde") == 5)
    val testLines = loadList("days/day08/test.txt")
    assert(day08.parseLine(testLines.head) == List(List("be", "cfbegad", "cbdgef", "fgaecd", "cgeb", "fdcge",
      "agebfd", "fecdb", "fabcd", "edb"), List("fdgacbe", "cefdb", "cefbgd", "gcbe")))
    assert(day08.pt2(testLines) == 61229)
    val inputLines = loadList("days/day08/input.txt")
    assert(day08.pt2(inputLines) == 1028926)

  }

}
