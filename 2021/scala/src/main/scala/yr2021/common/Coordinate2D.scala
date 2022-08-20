package yr2021.common

case class Coordinate2D(x: Int, y: Int) {
  def + (other: Coordinate2D) = Coordinate2D(x+other.x, y+other.y)
  def - (other: Coordinate2D) = this + (other * -1)
  def * (other: Int) = Coordinate2D(x*other, y*other)
  def == (other: Coordinate2D): Boolean = x == other.x && y == other.y
  def != (other: Coordinate2D): Boolean = ! ==(other)
  def manhattenDistance(other: Coordinate2D) = math.abs(x-other.x) + math.abs((y-other.y))
  def isAdjacent(other: Coordinate2D) = !=(other) && math.abs(x-other.x) < 2 && math.abs(y-other.y) < 2
}
object Coordinate2D {
  def apply(integers: Seq[Int]):Coordinate2D = Coordinate2D(integers(0), integers(1))
}

