package yr2021.common

import yr2021.common.InputData.groupWhen

import scala.io.Source

case class InputData(lines: List[String]) {



  def multiLineRecordParse(separater: String=>Boolean): List[List[String]] = groupWhen(separater)(lines)
}



object InputData {
  def emptyLineSeparater(line:String) = line.size>0

  def groupWhen[A] (f: A => Boolean)(xs: List[A]): List[List[A]] = {
    val fr = xs.foldLeft((List[List[A]](), List[A]()))( (a, e)=> if (f(e))((a._1, e :: a._2)) else (a._2.reverse :: a._1, Nil))
    (fr._2.reverse :: fr._1).reverse
  }

  def loadAsString(filename: String): String =
    Source.fromResource(filename).mkString

  def fromFile(filename: String): InputData =
    InputData(loadAsString(filename).split('\n').map(_.toString).toList)

}