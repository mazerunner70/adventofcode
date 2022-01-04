package yr2021.days

import yr2021.common.Util
import yr2021.common.Util.TupleProduct


class Day05 {

  def parseLine(line: String): List[(Int, Int)] =
    line.split(" -> ")
      .map(x=> x.split(",").map(_.toInt))
      .map(x=>(x(0), x(1)))
      .toList

  def calcStepSize(delta:(Int, Int)): (Int, (Int, Int)) = {
    if (delta.product == 0)
      (math.abs(delta._1+delta._2), (math.signum(delta._1), math.signum(delta._2)))
    else {
      val gcd = math.abs(Util.gcd(delta._1, delta._2))
      (math.abs(gcd), (delta._1/gcd, delta._2/gcd))
    }
  }

  def calcInterveningPoints(points: List[(Int, Int)]): List[(Int, Int)] = points match {
    case start :: end :: Nil => {
      val delta = (end._1-start._1, end._2-start._2)
      val (stepCount, stepSize) = calcStepSize(delta)
      (for (i <- 0 to stepCount) yield Util.tupleAdd(start, Util.tupleMultiply(stepSize, i)))
        .toList
    }
    case _ => throw new Exception("needed two points only")
  }
  implicit class Overlap(val points: List[(Int, Int)]) {
    def overlapPoints =
      points.groupBy(x => x)
        .filter(x => x._2.size > 1)
        .keySet
  }
  def pt1LineFilter(line: List[(Int, Int)]): Boolean =
    line(0)._1 == line(1)._1 || line(0)._2 == line(1)._2

  def pt2LineFilter(line: List[(Int, Int)]): Boolean = {
    if (line(0)._1 == line(1)._1 || line(0)._2 == line(1)._2)
      true
    else
      math.abs(line(1)._1-line(0)._1) == math.abs(line(1)._2-line(0)._2)
  }

  def getOverlaps(list: List[String], lineFilter:List[(Int, Int)]=>Boolean) = {
    list.map(parseLine(_))
      .filter(x=>lineFilter(x))
      .map(calcInterveningPoints(_))
      .flatten
      .overlapPoints
      .size
  }

  def pt1(list: List[String])= {
    getOverlaps(list, pt1LineFilter)
  }

  def pt2(list: List[String])= {
    getOverlaps(list, pt2LineFilter)
  }



}
