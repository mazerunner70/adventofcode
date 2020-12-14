package yr2020.day10

import yr2020.common.Util.loadList

import scala.annotation.tailrec

object Day10 {

  def part1(list: List[Int]): Int = {
    val intervals = list.sorted.sliding(2).map{case List(v1, v2) => v2-v1}.toList
    println(list.sorted)
    val groupedInterval = intervals.groupBy(t=>t)
    println(groupedInterval)
    val sizedinterval = groupedInterval.map(t=> (t._1, t._2.size))
    sizedinterval(1)*sizedinterval(3)
  }

  @tailrec
  def analyseSingleJoltSequences(sjsList: List[Int], total: Long = 1): Long = sjsList match {
    case Nil => total
    case h :: tail => {
      val newTotal = h match {
        case 2 | 3 => total * 2
        case 4     => total * 7 / 4 // Black magic - needs more modelling to understand this number
        case _     => total
      }
      analyseSingleJoltSequences(tail, newTotal)
    }
  }

  def part2(adaptors: List[Int], joltTolerence: Int): Long = {
    val sortedList = adaptors.sorted
    val increments = 0::sortedList.sliding(2).map{ window => window.last - window.head}.toList
    val singleIncrementSequences =
      (1 to 3).toList.map (increments.take(_).reverse.takeWhile(_ == 1).size) :::
        increments.sliding(4).map{ window => window.reverse.takeWhile(_ == 1).size}.toList
    analyseSingleJoltSequences(singleIncrementSequences)
  }


  def main(args: Array[String]): Unit = {
    val list = loadList("day01-10/day10/input.txt").map(_.toInt)
    val highest = list.max+3
    val addedends = 0 :: highest :: list
    println(part1(addedends))
    println(part2(addedends, 3))

  }

}
