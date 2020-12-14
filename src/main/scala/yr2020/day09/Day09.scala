package yr2020.day09

import yr2020.common.Util.{loadList, sumPairing}

import scala.annotation.tailrec

object Day09 {

  def rule(list: List[Long], value: Long): Boolean = {
    sumPairing(list, value) > -1
  }

  @tailrec
  def slideWindow(list: List[Long], results: List[(Boolean, Long)], rule: (List[Long], Long)=> Boolean, preambleSize: Int): List[(Boolean, Long)] = {
    val slice = list.take(preambleSize+1)
    val value = slice.last
    val result = rule(slice.dropRight(1), value)
    if (list.size == preambleSize+1)
      (result, value):: results
    else
      slideWindow(list.tail, (result, value) :: results, rule, preambleSize)
  }

  @tailrec
  def sumContiguous(list: List[Long], candidateList: List[Long], target:Long): Option[List[Long]] = {
    val total = candidateList.sum
    if (total == target)
      Some(candidateList)
    else if (total > target || list.size == 0)
      None
    else
      sumContiguous(list.tail, list.head :: candidateList, target)
  }
  def slideWindow2(list: List[Long], target: Long):  List[Long] = {
    val candidates = sumContiguous(list, List(), target)
    if (candidates.isDefined)
      candidates.get
    else
      slideWindow2(list.tail, target)
  }


  def main(args: Array[String]): Unit = {
    val list = loadList("day01-10/day09/input.txt").map(_.toLong)
    val invalidNumber = slideWindow(list, List(), rule, 25).filter(_._1 == false)(0)._2
    println(invalidNumber)
    val contigNumbers = slideWindow2(list, invalidNumber)
    println(contigNumbers)
    println(contigNumbers.min, contigNumbers.max)
    println(contigNumbers.min + contigNumbers.max)
  }

}
