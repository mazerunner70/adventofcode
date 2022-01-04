package yr2021.days
import scala.math.Ordered._

case class Coordinate(x: Int, y: Int) {
  def + (other: Coordinate) = Coordinate(x+other.x, y+other.y)
}

case class Grid(grid: List[List[Int]]) {
  val xSize = grid.headOption match {
    case Some(row) => row.size
    case None      => 0
  }
  val ySize = grid.size
  def get(coord: Coordinate) = grid(coord.y)(coord.x)
}

class Day09 {
  def withinRange(lower: Int, upper: Int, value: Int): Boolean = (lower <= value) && (value < upper)

  def isInside(coord: Coordinate, grid: Grid): Boolean = {
    withinRange(0, grid.xSize ,coord.x) &&
      withinRange(0, grid.ySize ,coord.y)
  }

  def adjacentCoords(coord: Coordinate, grid: Grid): Seq[Coordinate] = {
    val t =for (
      delta <- List(Coordinate(0,-1), Coordinate(0,1), Coordinate(-1, 0), Coordinate(1, 0))
      if isInside(coord+delta, grid) == true
    ) yield coord+delta
    t
  }

  def lowPoint(coord: Coordinate, grid: Grid): Boolean =
    grid.get(coord) < adjacentCoords(coord, grid).map(grid.get(_)).min

  def getLowPointCoords(grid: Grid): Seq[Coordinate] = {
    for (
      x <- 0 until grid.xSize;
      y <- 0 until grid.ySize;
      candidate = Coordinate(x, y)
      if lowPoint(candidate, grid)
    ) yield candidate
  }

  def parseLine(line: String): List[Int] = line.map(_.asDigit).toList
  def asGrid(lines: List[String]) = Grid(lines.map(parseLine(_)))

  def pt1(lines: List[String]) = {
    val grid: Grid = asGrid(lines)
    val lowPointCoords = getLowPointCoords(grid)
    lowPointCoords.map(grid.get(_)+1).sum
  }


  def gridWalk(grid: Grid, options: List[Coordinate], found: List[Coordinate]): List[Coordinate] = {
    def alreadyWalked(coordinate: Coordinate) = found contains coordinate
    def alreadyOption(coordinate: Coordinate) = options contains coordinate
    def heightNine(coordinate: Coordinate) = grid.get(coordinate) == 9
    options match {
      case coord :: _ => {
        val newOptions = adjacentCoords(coord, grid).filterNot(c=>alreadyWalked(c) || alreadyOption(c) || heightNine(c)).toList
        gridWalk(grid, newOptions ++ options.tail , coord :: found)
      }
      case Nil => found
    }
  }

  def pt2(lines: List[String]) = {
    val grid: Grid = asGrid(lines)
    val lowPointCoords = getLowPointCoords(grid)
    val basinSizes = for (
      lowPoint <- lowPointCoords
    ) yield gridWalk(grid, List(lowPoint), List()).size
    basinSizes.sorted.takeRight(3).product
  }

}
