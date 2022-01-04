package yr2021

import scala.io.Source

package object days {

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def loadList(filename: String): List[String] =
    loadFromFile(filename).split('\n').map(_.toString).toList

}
