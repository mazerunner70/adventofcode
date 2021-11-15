package yr2020.day11T20.day13

import yr2020.common.Util.loadList

import scala.annotation.tailrec

object Day13 {

  def lowestMultipleOverBar(value: Long, bar: Long) =
    (bar/value) * value + value

  def part1(list: List[String]) = {
    val startTime = list(0).toLong
    val busList = list(1).split(",").flatMap(_.toIntOption)
    val busTimes = busList.map(lowestMultipleOverBar(_, startTime))
    println(busTimes.toList)
    val busTime = busTimes.min
    val busId = busList(busTimes.indexOf(busTime))
    println(busId, (busTime-startTime), busId * (busTime-startTime))
  }

//  def getNextCandidatePattern(currentPattern: List[(Long, Long,Long)], seed: (Long, Long,Long)): List[(Long, Long,Long)] = {
//    @tailrec
//    def findNextBusTime(bus:(Long, Long,Long), reference: (Long, Long,Long)): Option[(Long, Long,Long)] = {
//      if (bus._3 -bus._2 < reference._3-reference._2){
//        findNextBusTime((bus._1, bus._2, bus._3+bus._1), reference)
//      } else {
//        if (bus._3 -bus._2 == reference._3-reference._2)
//          Some(bus)
//        else
//          None
//      }
//    }
//
//    def checkSuccess(currentPattern: List[(Long, Long,Long)]): Boolean = {
//      currentPattern.sliding(2).forall(x=> x(0)._3-x(0)._2 == x(1)._3-x(1)._2)
//    }
//
//    val seed = (currentPattern(0)._1, currentPattern(0)._2, currentPattern(0)._3+currentPattern(0)._1)
//
//    val newPattern = currentPattern.foldLeft(List[(Long, Long,Long)](seed))((list, element)=> list :+ findNextBusTime(element, list.last)).tail
//    println(newPattern.toList)
//    if (checkSuccess(newPattern))
//      newPattern
//    else
//      getNextCandidatePattern(newPattern)
//  }
//

  @tailrec
  def getNextCandidatePattern1(startPattern: List[(Int, Int)], current: Long, ctr: Long): Long = {
    if (ctr % 10000000 == 0) println(f" $ctr%,d  $current%,d")
    @tailrec
    def checkSuccess(pattern: List[(Int, Int)], reference: Long): Boolean = pattern match {
      case h :: t => if (((reference + h._2) % h._1) == 0L) checkSuccess(t, reference) else false
      case Nil    => true
    }
    if (checkSuccess(startPattern, current))
      current
    else
      getNextCandidatePattern1(startPattern, current+startPattern(0)._1, ctr + 1)

  }

  def part2(list: List[String]) = {
    val busList = list(1).split(",").map(_.toIntOption).zipWithIndex.filterNot(_._1 == None).map(x=>(x._1.get, x._2))
    println(busList.toList)
    val startPattern = busList.toList
    val pattern = getNextCandidatePattern1(startPattern, 0L, 1L)
    println(pattern)
  }


  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day13/input.txt")
    part1(list)
    part2(list)
  }

}
