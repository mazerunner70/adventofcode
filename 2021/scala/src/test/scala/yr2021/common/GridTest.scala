package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class GridTest extends AnyFlatSpec {

  behavior of "GridTest"

  it should "get" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.xSize == 2)
    assert(grid.ySize == 3)
    assert(grid.get(Coordinate2D(1, 2)) == 6)
  }

  it should "withinRange" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.withinRange(0, 6, 7) == false)
  }

  it should "isInside" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.isInside(Coordinate2D(4, 3)) == false)
  }

  it should "getNeighbours" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.getNeighbours(Coordinate2D(1, 2), List(Coordinate2D(1,0))) ==  List())
    assert(grid.getNeighbours(Coordinate2D(1, 2), List(Coordinate2D(1,2), Coordinate2D(-1,0))) ==  List(Coordinate2D(0,2)))
  }


  it should "transformGrid" in {
    val deltas = Seq(Coordinate2D(0,1))
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    def transformer(coordinate: Coordinate2D, neighbours: Seq[Coordinate2D]) =
      grid.get(coordinate) + neighbours.map(grid.get(_)).sum
    assert(grid.transformGrid(deltas, transformer) == List(Vector(4, 6), Vector(8, 10), Vector(5, 6)))
  }


  it should "parseLineString2Int" in {

  }

}
