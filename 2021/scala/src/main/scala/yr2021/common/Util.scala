package yr2021.common

import scala.annotation.tailrec
import scala.io.Source

object Util {

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def loadList(filename: String): List[String] =
    loadFromFile(filename).split('\n').map(_.toString).toList

  def multiLineRecordParse(list: List[String]): List[List[String]] = list.span(_.size > 0)  match {
    case (block, rest) if rest.size == 0 => block :: Nil
    case (block, rest) if rest.size > 1 => block :: multiLineRecordParse(rest.tail)
  }


  def sumPairing(list: List[Long], total: Long): Long = {
    list.find(it => list.contains(total - it)).getOrElse(-1)
  }

  def gcd(a: Long, b: Long): Long =
    if (b == 0) a else gcd(b, a % b)

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  //https://rosettacode.org/wiki/Modular_inverse#Scala
  def gcdExt(u: Long, v: Long): (Long, Long, Long) = {
    @tailrec
    def aux(a: Long, b: Long, x: Long, y: Long, x1: Long, x2: Long, y1: Long, y2: Long): (Long, Long, Long) = {
      if (b == 0) (x, y, a) else {
        val (q, r) = (a / b, a % b)
        aux(b, r, x2 - q * x1, y2 - q * y1, x, x1, y, y1)
      }
    }

    aux(u, v, 1, 0, 0, 1, 1, 0)
  }

  def moduloInverse(a: Long, m: Long): Option[Long] = {
    val (i, j, g) = gcdExt(a, m)
    if (g == 1) Option(if (i < 0) i + m else i) else Option.empty
  }

  def modInv(a: Long, m: Long, x: Long = 1, y: Long = 0): Long = if (m == 0) x else modInv(m, a % m, y, x - y * (a / m))

  def modi(a: Long, m: Long): Long =
    BigInt(a).modInverse(BigInt(m)).toLong

  implicit class TupleProduct(val p: Product) {
    def product = {
      p.productIterator.collect {
        case x: java.lang.Number => x.intValue()
      }.product
    }
  }

  def tupleAdd(tupleA: (Int, Int), tupleB: (Int, Int)): (Int, Int) =
    (tupleA._1+tupleB._1, tupleA._2+tupleB._2)
  def tupleMultiply(tuple: (Int, Int), magnitude: Int): (Int, Int) = {
    (tuple._1 * magnitude, tuple._2 * magnitude)
  }

  // http://rosettacode.org/wiki/Averages/Median#Scala
  def median[T](s: Seq[T])(implicit n: Integral[T]) = {
    import n._
    val (lower, upper) = s.sortWith(_<_).splitAt(s.size / 2)
    if (s.size % 2 == 0) (lower.last + upper.head) / fromInt(2) else upper.head
  }

  def triangle(number: Int): Int = number*(number+1)/2

  def distributionMap[A](list: List[A]): Map[A, Int] = {
    list.groupBy(e=>e).map(e=>(e._1, e._2.size))
  }

}
