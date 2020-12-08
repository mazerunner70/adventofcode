package yr2020.common

import scala.io.Source

object Util {

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def loadList(filename: String): List[String] =
    loadFromFile(filename).split('\n').map(_.toString).toList
}