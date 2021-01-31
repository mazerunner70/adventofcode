package yr2020.day11T20.day18

import yr2020.common.Util.loadList

object Day18Pt1 {

  def sumEachLine(lines: List[String]): Long = lines match {
    case h :: t => Pt1Evaluator.calculate(h) + sumEachLine(t)
    case Nil => 0
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day18/input.txt")
    val result = sumEachLine(list)
    println(result)
  }

}
