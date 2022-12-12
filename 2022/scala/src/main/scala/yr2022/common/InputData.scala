package yr2022.common

import yr2022.common.InputData.groupWhen

import scala.io.Source

case class InputData(lines: List[String]) {
  def asLineList: List[String] = lines
  def multiLineRecordParse(separater: String=>Boolean): List[InputData] = groupWhen(separater)(lines)
  def head = lines.head
  def tail = lines.tail
}



object InputData {
  def emptyLineSeparater(line:String) = line.size>0

  def groupWhen (f: String => Boolean)(xs: List[String]): List[InputData] = {
    val init: (List[InputData], List[String]) = (List(), List())
    val fr = xs.foldLeft(init)( (a, e)=> if (f(e))((a._1, e :: a._2)) else (InputData(a._2.reverse) :: a._1, Nil))
    (InputData(fr._2.reverse) :: fr._1).reverse
  }

  def loadAsString(filename: String): String =
    Source.fromResource(filename).mkString

  def fromFile(filename: String): InputData =
    InputData(loadAsString(filename).split('\n').map(_.toString).toList)

}