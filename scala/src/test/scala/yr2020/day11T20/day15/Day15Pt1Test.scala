package yr2020.day11T20.day15

import org.scalatest.flatspec.AnyFlatSpec

class Day15Pt1Test extends AnyFlatSpec {

  behavior of "Day15Pt1Test"

  it should "doGameTurn" in {
    println(Day15Pt1.doGameTurn(List(0,3,6).reverse, 1, 2020))
    assert(Day15Pt1.doGameTurn(List(0,3,6).reverse, 1, 2020)._2 == 436)
    assert(Day15Pt1.doGameTurn(List(1,3,2).reverse, 1, 2020)._2 == 1)
    assert(Day15Pt1.doGameTurn(List(2,1,3).reverse, 1, 2020)._2 == 10)
    assert(Day15Pt1.doGameTurn(List(1,2,3).reverse, 1, 2020)._2 == 27)
    assert(Day15Pt1.doGameTurn(List(2,3,1).reverse, 1, 2020)._2 == 78)
    assert(Day15Pt1.doGameTurn(List(3,2,1).reverse, 1, 2020)._2 == 438)
    assert(Day15Pt1.doGameTurn(List(3,1,2).reverse, 1, 2020)._2 == 1836)

    assert(Day15Pt1.doGameTurn(List(0,3,6).reverse, 1, 30000000)._2 == 175594)

  }

}
