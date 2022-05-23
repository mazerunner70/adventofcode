package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class GridTest extends AnyFlatSpec {

  behavior of "GridTest"

  it should "get" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.xSize == 2)
    assert(grid.ySize == 3)
    assert(grid.get(Coordinate(1, 2)) == 6)
  }

  it should "withinRange" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.withinRange(0, 6, 7) == false)
  }

  it should "isInside" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.isInside(Coordinate(4, 3)) == false)
  }

  it should "getNeighbours" in {
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    assert(grid.getNeighbours(Coordinate(1, 2), List(Coordinate(1,0))) ==  List())
    assert(grid.getNeighbours(Coordinate(1, 2), List(Coordinate(1,2), Coordinate(-1,0))) ==  List(Coordinate(0,2)))
  }


  it should "transformGrid" in {
    val deltas = Seq(Coordinate(0,1))
    val grid = Grid.asGrid(List("12","34", "56"), Grid.parseLineString2Int)
    def transformer(coordinate: Coordinate, neighbours: Seq[Coordinate]) =
      grid.get(coordinate) + neighbours.map(grid.get(_)).sum
    assert(grid.transformGrid(deltas, transformer) == List(Vector(4, 6), Vector(8, 10), Vector(5, 6)))
  }


  it should "parseLineString2Int" in {

  }

}
