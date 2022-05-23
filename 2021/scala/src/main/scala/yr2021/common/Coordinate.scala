package yr2021.common

case class Coordinate(x: Int, y: Int) {
  def + (other: Coordinate) = Coordinate(x+other.x, y+other.y)
  def * (other: Int) = Coordinate(x*other, y*other)
  def == (other: Coordinate): Boolean = x == other.x && y == other.y
  def != (other: Coordinate): Boolean = ! ==(other)
  def manhattenDistance(other: Coordinate) = math.abs(x-other.x) + math.abs((y-other.y))
  def isAdjacent(other: Coordinate) = !=(other) && math.abs(x-other.x) < 2 && math.abs(y-other.y) < 2
}
object Coordinate {
  def apply(integers: Seq[Int]):Coordinate = Coordinate(integers(0), integers(1))
}
