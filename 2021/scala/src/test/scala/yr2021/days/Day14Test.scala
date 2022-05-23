package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day14Test extends AnyFlatSpec {

  behavior of "Day14Test"

  it should "pt1-test" in {
    val day14 = new Day14
    val lines = loadList("days/day14/test.txt")
    assert(day14.pt1raw(lines, 1) == "NCNBCHB")
    assert(day14.pt1raw(lines, 2) == "NBCCNBBBCBHCB")
    assert(day14.pt1raw(lines, 3) == "NBBBCNCCNBBNBNBBCHBHHBCHB")
    assert(day14.pt1raw(lines, 4) == "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB")
    assert(day14.pt1raw(lines, 10).size == 3073)
    assert(day14.pt1(lines, 10) == 1588)
  }
  it should "pt1" in {
    val day14 = new Day14
    val lines = loadList("days/day14/input.txt")
    assert(day14.pt1raw(lines, 1) == "BVNKBNBVNOCNFSHNHKKBOPSBCSHKBPKVKKSFHNN")
    assert(asMap(day14.pt1raw(lines, 1)) == Map('N' -> 7, 'F' -> 2, 'V' -> 3, 'B' -> 6, 'P' -> 2, 'C' -> 2, 'H' -> 4, 'K' -> 7, 'O' -> 2, 'S' -> 4))
    assert(asMap(day14.pt1raw(lines, 2)) == Map('N' -> 10, 'F' -> 6, 'V' -> 11, 'B' -> 6, 'P' -> 4, 'C' -> 5, 'H' -> 9, 'K' -> 13, 'O' -> 2, 'S' -> 11))
    assert(asMap(day14.pt1raw(lines, 3)) == Map('N' -> 23, 'F' -> 12, 'V' -> 20, 'B' -> 10, 'P' -> 10, 'C' -> 14, 'H' -> 19, 'K' -> 22, 'O' -> 5, 'S' -> 18))
    assert(asMap(day14.pt1raw(lines, 4)) == Map('N' -> 49, 'F' -> 22, 'V' -> 44, 'B' -> 16, 'P' -> 30, 'C' -> 25, 'H' -> 34, 'K' -> 39, 'O' -> 13, 'S' -> 33))
    assert(day14.pt1(lines, 10) == 3009)
  }
  def asMap(string:String) = string.groupBy(e=>e).map(e=>(e._1-> e._2.length))
  it should "pt2-test" in {
    val day14 = new Day14
    val lines = loadList("days/day14/test.txt")
    assert(day14.pt2raw(lines, 1) == Map('N' -> 2, 'B' -> 2, 'C' -> 2, 'H' -> 1))
    assert(day14.pt2raw(lines, 1) == asMap("NCNBCHB"))
    assert(day14.pt2raw(lines, 1).map(_._2).sum == 7)
    assert(day14.pt2raw(lines, 2).map(_._2).sum == 13)
    assert(day14.pt2raw(lines, 2) == asMap("NBCCNBBBCBHCB"))
    assert(day14.pt2raw(lines, 3).map(_._2).sum == 25)
    assert(day14.pt2raw(lines, 3) == asMap("NBBBCNCCNBBNBNBBCHBHHBCHB"))
    assert(day14.pt2raw(lines, 4).map(_._2).sum == 49)
    assert(day14.pt2raw(lines, 4) == asMap("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"))
    assert(day14.pt2(lines, 10) == 1588)
    assert(day14.pt2(lines, 40) == 2188189693529L)
  }
  it should "pt2" in {
    val day14 = new Day14
    val lines = loadList("days/day14/input.txt")
    assert(day14.pt2raw(lines, 1).map(_._2).sum == 39)
    assert(day14.pt2raw(lines, 1) == Map('N' -> 7, 'F' -> 2, 'V' -> 3, 'B' -> 6, 'P' -> 2, 'C' -> 2, 'H' -> 4, 'K' -> 7, 'O' -> 2, 'S' -> 4))
    assert(day14.pt2raw(lines, 2) == Map('N' -> 10, 'F' -> 6, 'V' -> 11, 'B' -> 6, 'P' -> 4, 'C' -> 5, 'H' -> 9, 'K' -> 13, 'O' -> 2, 'S' -> 11))
    assert(day14.pt2raw(lines, 3) == Map('N' -> 23, 'F' -> 12, 'V' -> 20, 'B' -> 10, 'P' -> 10, 'C' -> 14, 'H' -> 19, 'K' -> 22, 'O' -> 5, 'S' -> 18))
    assert(day14.pt2raw(lines, 4) == Map('N' -> 49, 'F' -> 22, 'V' -> 44, 'B' -> 16, 'P' -> 30, 'C' -> 25, 'H' -> 34, 'K' -> 39, 'O' -> 13, 'S' -> 33))
    assert(day14.pt2(lines, 10) == 3009)
    assert(day14.pt2(lines, 40) == 1588)
  }
}
