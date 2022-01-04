package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day05Test extends AnyFlatSpec {

  behavior of "Day05Test"

  it should "pt1" in {
    val day05 = new Day05()
    val testList = Util.loadList("days/day05/test.txt")
    assert(day05.parseLine("0,9 -> 5,9") == List((0,9), (5,9)))
    assert(day05.calcInterveningPoints(day05.parseLine("0,9 -> 5,9")) == List((0,9), (1,9), (2,9), (3,9), (4,9), (5,9)))
    assert(day05.calcInterveningPoints(day05.parseLine("2,2 -> 2,1")) == List((2,2), (2,1)))
    val points = testList.map(day05.parseLine(_))
      .filter(x=>x(0)._1 == x(1)._1 || x(0)._2 == x(1)._2)
    assert(points == List(List((0,9), (5,9)), List((9,4), (3,4)), List((2,2), (2,1)), List((7,0), (7,4)), List((0,9), (2,9)), List((3,4), (1,4))))
    assert(
      testList.map(day05.parseLine(_))
        .filter(x=>x(0)._1 == x(1)._1 || x(0)._2 == x(1)._2)
        .map(day05.calcInterveningPoints(_)).flatten
        .groupBy(x=>x)
        .filter(x=>x._2.size>1)
      == Map((2,9) -> List((2,9), (2,9)), (3,4) -> List((3,4), (3,4)), (1,9) -> List((1,9), (1,9)), (0,9) -> List((0,9), (0,9)), (7,4) -> List((7,4), (7,4)))
    )
    val values = testList.map(day05.parseLine(_))
      .filter(x=>x(0)._1 == x(1)._1 || x(0)._2 == x(1)._2)
      .map(day05.calcInterveningPoints(_))
      .flatten
    assert(day05.Overlap(values).overlapPoints == Set((2,9), (3,4), (1,9), (0,9), (7,4)))
    assert(day05.pt1(testList) == 5)
    val list = Util.loadList("days/day05/input.txt")
    assert(day05.pt1(list) == 5576)
  }

  it should "pt2" in {
    val day05 = new Day05()
    val testList = Util.loadList("days/day05/test.txt")
    assert(day05.calcInterveningPoints(day05.parseLine("6,4 -> 2,0")) == List((6,4), (5,3), (4,2), (3,1), (2,0)))
    assert(day05.pt2(testList) == 12)
    assert(day05.pt2LineFilter(List((0,0), (2, 2)))== true)
    assert(day05.pt2LineFilter(List((0,0), (3, 2)))== false)
    val list = Util.loadList("days/day05/input.txt")
    assert(day05.pt2(list) == 18144)
  }


  }
