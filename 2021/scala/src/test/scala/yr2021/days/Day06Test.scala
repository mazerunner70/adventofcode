package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day06Test extends AnyFlatSpec {

  behavior of "Day06Test"

  it should "pt1" in {
    val day06 = new Day06()
    val testList = Util.loadList("days/day06/test.txt")
    assert(day06.pt1(testList, 1) == 5)
    assert(day06.pt1(testList, 2) == 6)
    assert(day06.pt1(testList, 3) == 7)
    assert(day06.pt1(testList, 4) == 9)
    assert(day06.pt1(testList, 6) == 10)
    assert(day06.pt1(testList, 10) == 12)
    assert(day06.pt1(testList, 18) == 26)
    assert(day06.pt1(testList, 80) == 5934)
    val list = Util.loadList("days/day06/input.txt")
    assert(day06.pt1(list, 80) == 346063)

  }

  it should "pt2" in {
    val day06 = new Day06()
    assert(day06.insertListToRegister(List(3,4,3,1,2)) == List(0, 0, 1, 2, 1, 1, 0))
    assert(day06.extractListFromRegister(List(0, 0, 1, 2, 1, 1, 0)) == List(4, 3, 3, 2, 1))
    assert(day06.shiftLeft(List(0, 0, 1, 2, 1, 1, 0), true, 0) == List(0, 0, 0, 1, 2, 1, 1))
    assert(day06.pt2NextDay((List(0, 0, 1, 2, 1, 1, 0), List(1,2))) == (List(2, 0, 0, 1, 2, 1, 1),List(0, 1)))
    val testList = Util.loadList("days/day06/test.txt")
    assert(day06.pt2(testList, 1) == 5)
    assert(day06.pt2(testList, 2) == 6)
    assert(day06.pt2(testList, 3) == 7)
    assert(day06.pt2(testList, 4) == 9)
    assert(day06.pt2(testList, 6) == 10)
    assert(day06.pt2(testList, 10) == 12)
    assert(day06.pt2(testList, 18) == 26)
    assert(day06.pt2(testList, 80) == 5934)
    assert(day06.pt2(testList, 256) == 26984457539L)
    val list = Util.loadList("days/day06/input.txt")
    assert(day06.pt2(list, 256) == 1572358335990L)

  }

//  it should "test256" in {
//    val day06 = new Day06()
//    val testList = Util.loadList("day01T10/day06/test.txt")
//
//  }
}
