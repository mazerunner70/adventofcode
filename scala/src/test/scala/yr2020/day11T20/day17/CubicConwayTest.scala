package yr2020.day11T20.day17

import org.scalatest.flatspec.AnyFlatSpec

class CubicConwayTest extends AnyFlatSpec {

  val test1 = """.#.
                |..#
                |###""".stripMargin

  behavior of "CubicConwayTest"

  it should "directions" in {
    val conway = new CubicConway().directions
  }

  it should "getValidPosAlongLine" in {
    val conway = new CubicConway()
    assert(conway.getValidPosAlongLine(List((1,1,1)), (-1, -1, -1), 1) ==
      List((0,0,0) ))
    assert(conway.getValidPosAlongLine(List((1,1,1)), (-1, -1, -1), 2) ==
      List((-1,-1,-1), (0,0,0) ))
    assert(conway.getValidPosAlongLine(List((1,1,1)), (1, 1, 1), 2) ==
      List((3,3,3), (2,2,2) ))
    assert(conway.getValidPosAlongLine(List((0,0,0)), (1, 1, 1), 2) ==
      List((2,2,2),(1,1,1) ))

  }

 it should "updateGridSize" in {
   val conway = new CubicConway()
   val grid = Day17Pt1.parseGrid(test1.linesIterator.toList, 0)
   assert(conway.updateGridSize(grid, ((0,0,0), (0,0,0))) ==
     ((0,0,0),(2,2,0)))
 }


  it should "calculate" in {
    val conway = new CubicConway()
    val newGrid = conway.calculate(Day17Pt1.parseGrid(test1.linesIterator.toList, 0))
    println(Day17Pt1.asGrid(newGrid, conway.updateGridSize(newGrid, ((0,0,0), (2, 2, 2)))))
    assert( newGrid ==
      Map((2,2,1) -> '#', (2,1,0) -> '#', (0,1,1) -> '#', (0,1,0) -> '#', (2,2,0) -> '#', (1,3,0) -> '#', (1,2,0) -> '#', (0,1,-1) -> '#', (1,3,-1) -> '#', (1,3,1) -> '#', (2,2,-1) -> '#'))
  }

  it should "countActiveNeighbours" in {
    val conway = new CubicConway()
    val grid = Day17Pt1.parseGrid(test1.linesIterator.toList, 0)
//    assert(conway.countActiveNeighbours((-1,-1,-1), Day17Pt1.parseGrid(test1.linesIterator.toList, 0) ) ==
//     0)
    println(grid)
    assert(conway.countActiveNeighbours((0,1,0), grid ) ==
      3)
  }

}
