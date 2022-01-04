package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day07Test extends AnyFlatSpec {

  behavior of "Day07Test"

  it should "pt1" in {
    val day07 = new Day07()
    val testList = Util.loadList("days/day07/test.txt")
    assert(day07.distance(day07.parseInput(testList), 3) == 39)
    assert(day07.distance(day07.parseInput(testList), 2) == 37)
    assert(day07.distance(day07.parseInput(testList), 10) == 71)
    assert(day07.distances(day07.parseInput(testList))(2) == 37)
    val distCalc = day07.distances(day07.parseInput(testList))
    assert(distCalc(10) == 71)
    assert(distCalc(3) == 39)
    assert(distCalc(2) == 37)
    assert(distCalc(1) == 41)
    val list = Util.loadList("days/day07/input.txt")
    assert(day07.distance(day07.parseInput(list), 330) == 347509)
    assert(day07.pt1(list) == 347509)
  }

  it should "pt2" in {
    val day07 = new Day07()
    val testList = Util.loadList("days/day07/test.txt")
    val distCalc = day07.triangularDistances(day07.parseInput(testList))
    assert(distCalc(2) == 206)
    assert(distCalc(1) == 242)
    assert(distCalc(3) == 183)
    assert(distCalc(4) == 170)
    assert(distCalc(5) == 168)
    assert(distCalc(6) == 176)
    assert(day07.pt2(testList) == 168)
    val list = Util.loadList("days/day07/input.txt")
    assert(day07.pt2(list) == 98257206)

  }

}
