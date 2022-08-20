package yr2021.common

import yr2021.days.{Day09Coordinate, Day09Grid}
case class Cell(coordinate: Coordinate2D, value: Int)

case class Grid private (grid: Vector[Vector[Int]]) {
  val xSize = grid(0).size
  val ySize = grid.size
  def withinRange(lower: Int, upper: Int, value: Int): Boolean = (lower <= value) && (value < upper)
  def isInside(coord: Coordinate2D): Boolean = {
    withinRange(0, xSize ,coord.x) &&
      withinRange(0, ySize ,coord.y)
  }
  def get(coord: Coordinate2D) = grid(coord.y)(coord.x)
  def getNeighbours(coordinate: Coordinate2D, deltas: Seq[Coordinate2D]): Seq[Coordinate2D] =
    for (
      delta <- deltas
      if isInside(coordinate+delta) == true
    ) yield coordinate+delta

  def transformGrid(deltas: Seq[Coordinate2D], transformer: (Coordinate2D, Seq[Coordinate2D])=>Int): Grid = {
    val result = for (
      y <- 0 until  ySize;
      x <- 0 until xSize;
      neighbours = getNeighbours(Coordinate2D(x, y), deltas)
    ) yield  transformer(Coordinate2D(x, y), neighbours)
    new Grid(result.toVector.grouped(xSize).toVector)
  }
  def filter(f: (Coordinate2D)=>Boolean): Seq[Coordinate2D] =
    for (
      y <- 0 until  ySize;
      x <- 0 until xSize;
      if f(Coordinate2D(x, y))
    ) yield Coordinate2D(x, y)
  def size: Int = xSize*ySize
  def values = grid.flatten
  def grow(i: Int): Grid = {
    val tb = Vector(Vector.fill(xSize+2)(i))
    Grid( tb ++ grid.map(r=>Vector(i) ++ r ++ Vector(i)) ++ tb )
  }
  def onEdge(c: Coordinate2D) =
    c.x == 0 || c.x == xSize-1 || c.y == 0 || c.y == ySize-1
}

object Grid {
  def parseLineString2Int(line: String): Vector[Int] = line.map(_.asDigit).toVector
  def asGrid(lines: List[String], lineParser: String=>Vector[Int]) = new Grid(lines.map(lineParser(_)).toVector)
}