package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day15V3Test extends AnyFlatSpec {

  behavior of "Day15V3Test"

  it should "pt1test" in {
    val day15V3 = new Day15V3
    val lines = loadList("days/day15/test.txt")
    assert(day15V3.pt1(lines, 99) == 40)
  }

  it should "pt1" in {
    val day15V3 = new Day15V3
    val lines = loadList("days/day15/input.txt")
    assert(day15V3.pt1(lines, 9999) == 589)
  }

  it should "pt2test" in {
    val day15V3 = new Day15V3
    val lines = loadList("days/day15/test.txt")
    assert(day15V3.pt2(lines, 2499) == 315)
  }

  it should "pt2" in {
    val day15V3 = new Day15V3
    val lines = loadList("days/day15/input.txt")
    assert(day15V3.pt2(lines, 500 * 500 -1) == 2885)
  }



}
