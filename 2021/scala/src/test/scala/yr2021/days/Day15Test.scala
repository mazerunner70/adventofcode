package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.dijkstraSP.v1
import yr2021.common.dijkstraSP.v1.DirectedEdge

class Day15Test extends AnyFlatSpec {

  behavior of "Day15Test"

  it should "parseGrid" in {
    val day15 = new Day15
    val lines = List("123", "456","789")
    assert(day15.parseGrid(lines) == List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)))
  }

  it should "asAdjacencyId" in {

  }

  it should "adjacents" in {
    val day15 = new Day15
    val lines = List("12", "45")
    assert(day15.adjacents(0,0,2,2) == List((1,0), (0,1)))
  }

  it should "asEdgeWeightedDigraph" in {
    val day15 = new Day15
    val lines = List("12", "45")
    assert(day15.asEdgeWeightedDigraph(day15.parseGrid(lines)).adjacency ==
      Map(0 -> List(v1.DirectedEdge(0,1,2.0), v1.DirectedEdge(0,2,4.0)),
        2 -> List(v1.DirectedEdge(2,3,5.0), v1.DirectedEdge(2,0,1.0)),
        1 -> List(v1.DirectedEdge(1,3,5.0), v1.DirectedEdge(1,0,1.0)),
        3 -> List(v1.DirectedEdge(3,2,4.0), v1.DirectedEdge(3,1,2.0))))
  }

  it should "pt1test" in {
    val day15 = new Day15
    val lines = loadList("days/day15/test.txt")
    day15.pt1(lines, 99) match {
      case Left(value) => assert(value == false)
      case Right(value) => assert(value == 40)
    }
  }

  it should "pt2Grid" in {
    val day15 = new Day15
    val lines = loadList("days/day15/test.txt")
    val result = loadList("days/day15/test2.txt")
    val pt2Grid = day15.pt2CalculateGrid(lines)
    val pt2result = day15.parseGrid(result)
    assert(pt2Grid == pt2result)
  }
  it should "pt1" in {
    val day15 = new Day15
    val lines = loadList("days/day15/input.txt")
    day15.pt1(lines, 9999) match {
      case Left(value) => assert(value == false)
      case Right(value) => assert(value == 589)
    }
  }

  it should "pt2test" in {
    val day15 = new Day15
    val lines = loadList("days/day15/test.txt")
    day15.pt2(lines, 2499) match {
      case Left(value) => assert(value == false)
      case Right(value) => assert(value == 315)
    }
  }
  it should "pt2" in {
    val day15 = new Day15
    val lines = loadList("days/day15/input.txt")
    day15.pt2(lines, 500 * 500 -1) match {
      case Left(value) => assert(value == false)
      case Right(value) => assert(value == 589)
    }
  }

  it should "v2pt1test" in {
    val day15 = new Day15
    val lines = loadList("days/day15/test.txt")
    val result = day15.v2Pt1(lines, 99)
    assert(result._1 == 40.0)
  }


  it should "v2pt1" in {
    val day15 = new Day15
    val lines = loadList("days/day15/input.txt")
    val result = day15.v2Pt1(lines, 100*100-1)
    assert(result == List())
  }


}
