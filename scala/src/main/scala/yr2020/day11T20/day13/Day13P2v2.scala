package yr2020.day11T20.day13

import yr2020.common.Util
import yr2020.common.Util.{gcd, loadList}

object Day13P2v2 {



  def moduloInverse(modFound: Long, modNeeded: Long, divisor: Long) = {
    if (modFound == modNeeded)
      1
    else
//      Util.modInv(modFound, divisor) * modNeeded
//      Util.moduloInverse(modFound, divisor).get * modNeeded
      Util.modi(modFound, divisor) * modNeeded
  }

  def crt(list:List[(Long, Long)]): Long = {
    val product = list.map(_._1).product
    println(list.toList)
    println(product)
//    val terms1 = list.map(bus=>product/bus._1)
//    println(terms1)
    val terms2 = list.map(bus=>(product/bus._1, moduloInverse(product/bus._1 % bus._1, bus._2, bus._1)))
    println(f"term2 = $terms2")
    val terms3 = list.map(bus=>product/bus._1 * moduloInverse(product/bus._1 % bus._1, bus._2, bus._1))
    println(terms3)
    println(terms3.sum, product)
    terms3.sum % product
  }

  def part2(list: List[String]) = {
    val busList = list(1).split(",").map(_.toLongOption).zipWithIndex.filterNot(_._1 == None).map(x=>(x._1.get, x._1.get-(x._2 % x._1.get)))
//    println(crt(busList.toList))
    val x = crt(busList.toList)
    println (x)
    busList.foreach(bus=>println(f"$bus: ${x}, divided by ${bus._1} gives ${(x)/bus._1} remainder ${(x) % bus._1}"))
    busList.foreach(bus=>println(f"$bus: ${x-bus._2}, divided by ${bus._1} gives ${(x-bus._2)/bus._1} remainder ${(x-bus._2) % bus._1}"))
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day13/input.txt")
    part2(list)
  }

}
