package yr2020.day05

import yr2020.common.Util.loadList

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Day05 {

  def convToBinary(line: String): Int = {
    Integer.parseInt(line.replaceAll("[F|L]", "0").replaceAll("[B|R]", "1"), 2)
  }

  def convToRowSeat(seatId:Int): (Int, Int) =
    (seatId >>> 3, seatId & 7)

  def mapToRowList(seatIds:List[Int]): Map[Int, List[Int]] = {
    seatIds.map(convToRowSeat(_)).groupMap(rs=>rs._1)(rs=>rs._2)
  }

  def findMissingEntry(list: List[Int]): List[Int] =
    list match {
      case _::Nil => Nil
      case h::h1::_ if h +1 == h1 => findMissingEntry(list.tail)
      case h::h1::_ if h +1 != h1 => h +1 :: findMissingEntry(list.tail)
    }

  def main(args: Array[String]): Unit = {
    val list = loadList("day05/input.txt")
    val seatIds = list.map(convToBinary(_))
    println(seatIds.max)
    println(findMissingEntry(list.map(convToBinary(_)).sorted)(0))
   }

}
