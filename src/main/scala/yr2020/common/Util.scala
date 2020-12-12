package yr2020.common

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

}