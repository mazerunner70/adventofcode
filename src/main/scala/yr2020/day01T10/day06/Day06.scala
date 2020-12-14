package yr2020.day01T10.day06

import yr2020.common.Util.{loadList, multiLineRecordParse}

import scala.annotation.tailrec

object Day06 {



  def intersection(strings: List[String]): String =
    strings.foldLeft("abcedefghijklmnopqrstuvwxyz" )( (l, r) => l.intersect(r))


  def main(args: Array[String]): Unit = {
    val list = loadList("day01-10/day06/input.txt")
    println(multiLineRecordParse(list).map(_.mkString("").distinct.size).sum)
    println(multiLineRecordParse(list).map(intersection(_).size).sum)
  }


}
