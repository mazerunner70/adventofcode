package yr2021.common

import yr2021.days.{Day09Coordinate, Day09Grid}
case class Cell(coordinate: Coordinate, value: Int)

class Grid(grid: List[List[Int]]) {
  val xSize = grid.headOption match {
    case Some(row) => row.size
    case None      => 0
  }
  val ySize = grid.size
  def withinRange(lower: Int, upper: Int, value: Int): Boolean = (lower <= value) && (value < upper)
  def isInside(coord: Coordinate): Boolean = {
    withinRange(0, xSize ,coord.x) &&
      withinRange(0, ySize ,coord.y)
  }
  def get(coord: Coordinate) = grid(coord.y)(coord.x)
  def getNeighbours(coordinate: Coordinate, deltas: Seq[Coordinate]): Seq[Coordinate] =
    for (
      delta <- deltas
      if isInside(coordinate+delta) == true
    ) yield coordinate+delta

  def transformGrid(deltas: Seq[Coordinate], transformer: (Coordinate, Seq[Coordinate])=>Int): Grid = {
    val result = for (
      y <- 0 until  ySize;
      x <- 0 until xSize;
      neighbours = getNeighbours(Coordinate(x, y), deltas)
    ) yield  transformer(Coordinate(x, y), neighbours)
    new Grid(result.toList.grouped(xSize).toList)
  }
  def filter(f: (Coordinate)=>Boolean): Seq[Coordinate] =
    for (
      y <- 0 until  ySize;
      x <- 0 until xSize;
      if f(Coordinate(x, y))
    ) yield Coordinate(x, y)
  def size: Int = xSize*ySize
  def values = grid.flatten
}

object Grid {
  def parseLineString2Int(line: String): List[Int] = line.map(_.asDigit).toList
  def asGrid(lines: List[String], lineParser: String=>List[Int]) = new Grid(lines.map(lineParser(_)))
}