package yr2021.common

import scala.collection.immutable.HashSet

case class Cell[A](c: CoordinateND, value: A){
  override def hashCode() = c.##
  override def equals(o:Any) = o match {
    case Cell(`c`, _) => true
    case _ => false
  }
}

case class CoordSet[A](cs: Set[Cell[A]]) {

  def filter(p: Cell[A]=>Boolean): CoordSet[A] =
    CoordSet(cs.filter(p))

  def put(ncs: Set[Cell[A]]): CoordSet[A] =
    CoordSet(cs--ncs++ncs)

}
