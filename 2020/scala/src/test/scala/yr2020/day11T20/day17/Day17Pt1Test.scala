package yr2020.day11T20.day17

import org.scalatest.flatspec.AnyFlatSpec

class Day17Pt1Test extends AnyFlatSpec {

  val test1 = """.#.
                |..#
                |###""".stripMargin

  behavior of "Day17Test"

  it should "parseGrid" in {
    assert(Day17Pt1.parseGrid(test1.linesIterator.toList, 0) ==
      Map((2,1,0) -> '#', (1,0,0) -> '#', (2,2,0) -> '#', (0,2,0) -> '#', (1,2,0) -> '#') )
  }

  it should "asGrid" in {
    val grid = Day17Pt1.parseGrid(test1.linesIterator.toList, 0)
    val gridSize = new CubicConway().updateGridSize(grid, ((0,0,0), (0,0,0)))

//    println( Day17Pt1.asGrid(grid, gridSize))
  }

  it should "iteration" in {
    val grid = Day17Pt1.parseGrid(test1.linesIterator.toList, 0)
    val conway = new CubicConway()
    val finalGrid = Day17Pt1.iteration(conway, grid, 6)
    val currentGridSize = conway.updateGridSize(finalGrid)
//    println( Day17Pt1.asGrid(finalGrid, currentGridSize))
    assert(finalGrid.size == 112)
  }

}
