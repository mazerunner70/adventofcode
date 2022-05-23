package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day09Test extends AnyFlatSpec {

  behavior of "Day08Test"

  it should "parseLine" in {
    val day09 = new Day09
    assert(day09.parseLine("123") == Seq(1, 2, 3))
  }

  it should "testGrid" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    val grid = day09.asGrid(testLines)
    assert (grid.xSize == 10)
    assert (grid.ySize == 5)
    assert (grid.get(Day09Coordinate(0,0)) == 2)
  }

  it should "withinRange" in {
    val day09 = new Day09
    assert(day09.withinRange(0,9,1) == true)
    assert(day09.withinRange(0,9,-1) == false)
  }

  it should "isInside" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    val grid = day09.asGrid(testLines)
    assert(day09.isInside(Day09Coordinate(-1,-1), grid) == false)
    assert(day09.isInside(Day09Coordinate(-1,0), grid) == false)
    assert(day09.isInside(Day09Coordinate(1,0), grid) == true)
    assert(day09.isInside(Day09Coordinate(0,-1), grid) == false)
    assert(day09.isInside(Day09Coordinate(0,1), grid) == true)
  }

  it should "adjacentCoords" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    val grid = day09.asGrid(testLines)
    assert(day09.adjacentCoords(Day09Coordinate(0,0), grid) == List(Day09Coordinate(0,1), Day09Coordinate(1,0)))
  }

  it should "getLowPointCoords" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    assert(day09.getLowPointCoords(day09.asGrid(testLines)) == Seq(Day09Coordinate(1,0), Day09Coordinate(2,2), Day09Coordinate(6,4), Day09Coordinate(9,0)))
  }

  it should "pt1-test" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    assert(day09.pt1(testLines) == 15)
  }
  it should "pt1" in {
    val day09 = new Day09
    val lines = loadList("days/day09/input.txt")
    assert(day09.pt1(lines) == 594)
  }

  it should "gridWalk" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    val grid = day09.asGrid(testLines)
    assert (day09.gridWalk(grid, List(Day09Coordinate(1,0)), List()) == List(Day09Coordinate(0,1), Day09Coordinate(0,0), Day09Coordinate(1,0)))
    assert (day09.gridWalk(grid, List(Day09Coordinate(0,0)), List()) == List(Day09Coordinate(1,0), Day09Coordinate(0,1), Day09Coordinate(0,0)))
  }

  it should "pt2-test" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/test.txt")
    assert(day09.pt2(testLines) == 1134)
  }
  it should "pt2" in {
    val day09 = new Day09
    val testLines = loadList("days/day09/input.txt")
    assert(day09.pt2(testLines) == 858494)
  }

}
