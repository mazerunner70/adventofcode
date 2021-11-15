package yr2020.day11T20.day15

import scala.annotation.tailrec

object Day15Pt1 {


  @tailrec
  final def doGameTurn(list: List[Int], turnNumber: Int, Limit: Int): (List[Int], Int) =  list.size match {
    case Limit => (list, list.head)
    case _ => doGameTurn(list.tail.indexOf(list.head)+1 :: list, turnNumber+1, Limit)
  }



  def main(args: Array[String]): Unit = {
    println(doGameTurn(List(2,0,1,7,4,14,18).reverse, 1,2020))
  }

}
