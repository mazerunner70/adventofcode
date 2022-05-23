package yr2021.days


case class Day09Coordinate(x: Int, y: Int) {
  def + (other: Day09Coordinate) = Day09Coordinate(x+other.x, y+other.y)
}

case class Day09Grid(grid: List[List[Int]]) {
  val xSize = grid.headOption match {
    case Some(row) => row.size
    case None      => 0
  }
  val ySize = grid.size
  def get(coord: Day09Coordinate) = grid(coord.y)(coord.x)
}

class Day09 {
  def withinRange(lower: Int, upper: Int, value: Int): Boolean = (lower <= value) && (value < upper)

  def isInside(coord: Day09Coordinate, grid: Day09Grid): Boolean = {
    withinRange(0, grid.xSize ,coord.x) &&
      withinRange(0, grid.ySize ,coord.y)
  }

  def adjacentCoords(coord: Day09Coordinate, grid: Day09Grid): Seq[Day09Coordinate] = {
    val t =for (
      delta <- List(Day09Coordinate(0,-1), Day09Coordinate(0,1), Day09Coordinate(-1, 0), Day09Coordinate(1, 0))
      if isInside(coord+delta, grid) == true
    ) yield coord+delta
    t
  }

  def lowPoint(coord: Day09Coordinate, grid: Day09Grid): Boolean =
    grid.get(coord) < adjacentCoords(coord, grid).map(grid.get(_)).min

  def getLowPointCoords(grid: Day09Grid): Seq[Day09Coordinate] = {
    for (
      x <- 0 until grid.xSize;
      y <- 0 until grid.ySize;
      candidate = Day09Coordinate(x, y)
      if lowPoint(candidate, grid)
    ) yield candidate
  }

  def parseLine(line: String): List[Int] = line.map(_.asDigit).toList
  def asGrid(lines: List[String]) = Day09Grid(lines.map(parseLine(_)))

  def pt1(lines: List[String]) = {
    val grid: Day09Grid = asGrid(lines)
    val lowPointCoords = getLowPointCoords(grid)
    lowPointCoords.map(grid.get(_)+1).sum
  }


  def gridWalk(grid: Day09Grid, options: List[Day09Coordinate], found: List[Day09Coordinate]): List[Day09Coordinate] = {
    def alreadyWalked(coordinate: Day09Coordinate) = found contains coordinate
    def alreadyOption(coordinate: Day09Coordinate) = options contains coordinate
    def heightNine(coordinate: Day09Coordinate) = grid.get(coordinate) == 9
    options match {
      case coord :: _ => {
        val newOptions = adjacentCoords(coord, grid).filterNot(c=>alreadyWalked(c) || alreadyOption(c) || heightNine(c)).toList
        gridWalk(grid, newOptions ++ options.tail , coord :: found)
      }
      case Nil => found
    }
  }

  def pt2(lines: List[String]) = {
    val grid: Day09Grid = asGrid(lines)
    val lowPointCoords = getLowPointCoords(grid)
    val basinSizes = for (
      lowPoint <- lowPointCoords
    ) yield gridWalk(grid, List(lowPoint), List()).size
    basinSizes.sorted.takeRight(3).product
  }

}
