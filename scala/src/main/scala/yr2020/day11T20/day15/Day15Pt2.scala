package yr2020.day11T20.day15

import scala.annotation.tailrec

object Day15Pt2 {


  @tailrec
  final def doGameTurn(mapOccurence: Map[Int, Int], lastNumber: Int, turnNumber: Int, Limit: Int): (Map[Int, Int], Int) = {
    val nextNumber = (turnNumber-mapOccurence.getOrElse(lastNumber, turnNumber))
    turnNumber match {
      case Limit => (mapOccurence, mapOccurence.maxBy(entry=>entry._2)._1)
      case _ => doGameTurn(mapOccurence + (lastNumber -> turnNumber), nextNumber, turnNumber+1, Limit)
    }
  }

  def doGame(startList: List[Int], Limit: Int): (Map[Int, Int], Int) = {
    val mapOccurence = startList.init.zipWithIndex.map(x=> x._1->(x._2+1)).toMap
    val lastNumber = startList.last
    doGameTurn(mapOccurence, lastNumber, startList.size, Limit+1)
  }

  def main(args: Array[String]): Unit = {
    println(doGame(List(2,0,1,7,4,14,18), 30000000))
  }

}
