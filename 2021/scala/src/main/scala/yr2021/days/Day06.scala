package yr2021.days

import scala.annotation.tailrec

class Day06 {

  def nextDayValue(now: Int): Int =
    if (now == 0) 6 else now-1

  def parseInput(lines: List[String]): List[Int] = lines match {
    case line :: Nil => line.split(",").map(_.toInt).toList
    case _           => throw new Exception("Invalid line count in input")
  }

  def nextDay(day: (List[Int], List[Int]), countdown: Int): (List[Int], List[Int]) = {
    val nextDay = day._1.map(nextDayValue(_))
    (nextDay++day._2, List.tabulate(nextDay.count(_ == 0))(x=>8))
  }

  def pt1(list: List[String], dayCount: Int): Int =  {
    def dayProcessor(day: (List[Int], List[Int]), countdown: Int): List[Int] = countdown match {
      case 0 => day._1
      case count => dayProcessor(nextDay(day, count), count-1)
    }
    dayProcessor((parseInput(list), List()), dayCount).size
  }

  // Part 2
  // To save on space lets create two rotating registers
  // One for juveniles width 2 and one for adult width 7
  // Each day the juveniles with 0 days left will be added to the adults in the 6 days left cell
  // Each day the adults with 0 days left will be added to the juveniles with 2 days left and to the adults with 6 days left

  def insertListToRegister(list: List[Int]): List[Long] =
    (for (i <- 6 to 0 by -1) yield (list.count(e=> i == e)).toLong).toList

  def extractListFromRegister(register: List[Long]): List[Long] =
    register.zipWithIndex.map(c=>List.tabulate(c._1.toInt)(x=>6-c._2.toLong)).flatten


  def shiftLeft(register: List[Long], rotate: Boolean, extraHead: Long) : List[Long] =
    if (rotate)
      extraHead+register.last :: register.init
    else
      extraHead :: register.init

  def pt2NextDay(lanternFish: (List[Long], List[Long])): (List[Long], List[Long]) = {
    val (adult, juvenile) = lanternFish
    val spawningAdults = adult.last
    val adolescents = juvenile.last
    (shiftLeft(adult, true, adolescents), shiftLeft(juvenile, false, spawningAdults))
  }

  @tailrec
  final def pt2Days(lanternFish: (List[Long], List[Long]), countdownDays: Int): (List[Long], List[Long]) = countdownDays match {
    case 0 => lanternFish
    case d => pt2Days(pt2NextDay(lanternFish), d-1)
  }

  def pt2(list: List[String],days: Int): Long = {
    val input = insertListToRegister(parseInput(list))
    val result = pt2Days((input, List(0,0)), days)
    result._1.sum+result._2.sum
  }

}
