package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Grid

class Day11Test extends AnyFlatSpec {

  behavior of "Day11Test"

  it should "stepPt3" in {
    val lines = List("11111", "19991", "19191", "19991", "11111")
    val grid = Grid.asGrid(lines, Grid.parseLineString2Int)
    val day11= new Day11
    val gridPt1 = day11.incEnergy(grid)
    val gridPt2 = day11.locateFlashers(gridPt1)
    assert (day11.resetFlashers(gridPt2.grid).values == List(3, 4, 5, 4, 3, 4, 0, 0, 0, 4, 5, 0, 0, 0, 5, 4, 0, 0, 0, 4, 3, 4, 5, 4, 3))

  }

  it should "stepPt2" in {
    val lines = List("11111", "19991", "19191", "19991", "11111")
    val grid = Grid.asGrid(lines, Grid.parseLineString2Int)
    val day11= new Day11
    val gridPt1 = day11.incEnergy(grid)
    assert (day11.locateFlashers(gridPt1).grid.values == List(3, 4, 5, 4, 3, 4, 13, 15, 13, 4, 5, 15, 10, 15, 5, 4, 13, 15, 13, 4, 3, 4, 5, 4, 3))

  }

  it should "stepPt1" in {
    val lines = List("11111", "19991", "19191", "19991", "11111")
    val grid = Grid.asGrid(lines, Grid.parseLineString2Int)
    val day11 = new Day11
    assert (day11.incEnergy(grid).values == List(2, 2, 2, 2, 2, 2, 10, 10, 10, 2, 2, 10, 2, 10, 2, 2, 10, 10, 10, 2, 2, 2, 2, 2, 2))
  }

  it should "step" in {
    val lines = List("11111", "19991", "19191", "19991", "11111")
    val grid = Grid.asGrid(lines, Grid.parseLineString2Int)
    val day11 = new Day11
    val outcome = day11.step(grid)
    assert(outcome.grid.values == List(3, 4, 5, 4, 3, 4, 0, 0, 0, 4, 5, 0, 0, 0, 5, 4, 0, 0, 0, 4, 3, 4, 5, 4, 3))
    assert(outcome.flashers.size == 9)
    val outcome2 = day11.step(outcome.grid)
    assert(outcome2.grid.values == List(4, 5, 6, 5, 4, 5, 1, 1, 1, 5, 6, 1, 1, 1, 6, 5, 1, 1, 1, 5, 4, 5, 6, 5, 4))
  }

  it should "step#2" in {
    val lns =
      """
        |6594254334
        |3856965822
        |6375667284
        |7252447257
        |7468496589
        |5278635756
        |3287952832
        |7993992245
        |5957959665
        |6394862637
        |""".stripMargin.replace("\n", "")
    val testLines = loadList("days/day11/test.txt")
    val day11 = new Day11
    val grid = Grid.asGrid(testLines, Grid.parseLineString2Int)
    val outcome = day11.step(grid)
    assert(outcome.grid.values == Grid.parseLineString2Int(lns))
  }

  it should "pt1" in {
    val testLines = loadList("days/day11/test.txt")
    val day11 = new Day11
    assert(day11.pt1(testLines, 10) == 204)
    assert(day11.pt1(testLines, 100) == 1656)
    val lines = loadList("days/day11/input.txt")
    assert(day11.pt1(lines, 100) == 1755)
  }

  it should "pt2" in {
    val testLines = loadList("days/day11/test.txt")
    val day11 = new Day11
    assert(day11.pt2(testLines) == 195)
    val lines = loadList("days/day11/input.txt")
    assert(day11.pt2(lines) == 212)
  }

}
