package yr2021.common

case class CoordinateND(terms: Vector[Int]) extends Ordered[CoordinateND] {
  import scala.math.Ordered.orderingToOrdered
  def + (other: CoordinateND) = CoordinateND((terms zip other.terms).map{case (a, b)=> a+b})
  def - (other: CoordinateND) = this + (other * -1)
  def * (other: Int) = CoordinateND(terms.map(_*other))
  def == (other: CoordinateND): Boolean = terms == other.terms
  def != (other: CoordinateND): Boolean = ! ==(other)
  def manhattenDistance(other: CoordinateND): Option[Int] = if (terms.size != other.terms.size) None else
    Some((terms zip other.terms).map(x=>math.abs(x._1-x._2)).sum)
  def distance(other: CoordinateND): Option[Double] = if (terms.size != other.terms.size) None else
    Some(math.sqrt((terms zip other.terms).map(x=>(x._1-x._2)*(x._1-x._2)).sum))


  def compare(other: CoordinateND): Int = {
    if (terms.size == other.terms.size) {
      for (i <- 0 until(terms.size)) {
        if ((terms(i) compare other.terms(i)) != 0) return terms(i) compare other.terms(i)
      }
      return 0
    }
    terms.size compare other.terms.size
  }
}

object CoordinateND {
  def origin(i: Vertex): CoordinateND = CoordinateND(Vector.fill(i)(0))
}
