package yr2020.day11T20.day15

import org.scalatest.flatspec.AnyFlatSpec

class Day15Pt2Test extends AnyFlatSpec {

  behavior of "Day15Pt2Test"

  it should "doGameTurn" in {
    println(Day15Pt2.doGame(List(0,3,6), 2021))
    assert(Day15Pt2.doGame(List(0,3,6), 2020)._2 == 436)
    assert(Day15Pt2.doGame(List(1,3,2), 2020)._2 == 1)
    assert(Day15Pt2.doGame(List(2,1,3), 2020)._2 == 10)
    assert(Day15Pt2.doGame(List(1,2,3), 2020)._2 == 27)
    assert(Day15Pt2.doGame(List(2,3,1), 2020)._2 == 78)
    assert(Day15Pt2.doGame(List(3,2,1), 2020)._2 == 438)
    assert(Day15Pt2.doGame(List(3,1,2), 2020)._2 == 1836)

    assert(Day15Pt2.doGame(List(0,3,6), 30000000)._2 == 175594)
    assert(Day15Pt2.doGame(List(1,3,2), 30000000)._2 == 2578)
    assert(Day15Pt2.doGame(List(2,1,3), 30000000)._2 == 3544142)
    assert(Day15Pt2.doGame(List(1,2,3), 30000000)._2 == 261214)
    assert(Day15Pt2.doGame(List(2,3,1), 30000000)._2 == 6895259)
    assert(Day15Pt2.doGame(List(3,2,1), 30000000)._2 == 18)
    assert(Day15Pt2.doGame(List(3,1,2), 30000000)._2 == 362)
  }

}
