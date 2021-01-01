package yr2020.common

import scala.annotation.tailrec
import scala.io.Source

object Util {

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def loadList(filename: String): List[String] =
    loadFromFile(filename).split('\n').map(_.toString).toList

  def multiLineRecordParse(list: List[String]): List[List[String]] = {
    val (h, t) = list.span(_.size > 0)
    (h, t) match {
      case x if x._2.size == 0 => h :: Nil
      case x if x._2.size != 0 => h :: multiLineRecordParse(t.tail)
    }
  }

  def sumPairing(list: List[Long], total: Long): Long = {
    list.find(it=>list.contains(total-it)).getOrElse(-1)
  }

  def gcd(a: Long, b: Long): Long =
    if (b == 0) a else gcd(b, a % b)

  //https://rosettacode.org/wiki/Modular_inverse#Scala
  def gcdExt(u: Long, v: Long): (Long, Long, Long) = {
    @tailrec
    def aux(a: Long, b: Long, x: Long, y: Long, x1: Long, x2: Long, y1: Long, y2: Long): (Long, Long, Long) = {
      if(b == 0) (x, y, a) else {
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

  def modInv(a: Long, m: Long, x:Long = 1, y:Long = 0) : Long = if (m == 0) x else modInv(m, a%m, y, x - y*(a/m))

  def modi(a: Long, m: Long): Long =
    BigInt(a).modInverse(BigInt(m)).toLong

}