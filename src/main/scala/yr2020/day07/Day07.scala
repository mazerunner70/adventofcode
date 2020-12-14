package yr2020.day07

import yr2020.common.Util.loadList

import scala.collection.IterableOnce.iterableOnceExtensionMethods

object Day07 {

  case class Bag (name: String, bags: List[Bag])

  val dottedBlack = Bag("Dotted Black", List())
  val fadedBlue = Bag("Faded Blue", List())
  val vibrantPlum = Bag("Vibrant Plum", List(fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, dottedBlack, dottedBlack, dottedBlack, dottedBlack, dottedBlack, dottedBlack))
  val darkOlive = Bag("Dark Olive", List(fadedBlue, fadedBlue, fadedBlue, dottedBlack, dottedBlack, dottedBlack, dottedBlack))
  val shinyGold = Bag("Shiny Gold", List(darkOlive, vibrantPlum, vibrantPlum))
  val mutedYellow = Bag("Muted Yellow", List(shinyGold, shinyGold, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue, fadedBlue))
  val brightWhite = Bag("Bright White", List(shinyGold))
  val lightRed = Bag("Light Red", List(brightWhite, mutedYellow, mutedYellow))
  val darkOrange = Bag("Dark Orange", List(brightWhite, brightWhite, brightWhite, mutedYellow, mutedYellow, mutedYellow, mutedYellow))
  val allBags = Bag("all bags", List(darkOrange, lightRed, brightWhite, mutedYellow, shinyGold, darkOlive, vibrantPlum, fadedBlue, dottedBlack))

  def bagLineParse1(line: String): (String, List[String]) = {
    def stripBag(string: String): String =
      string.replaceAll(" bag.*", "")
    val result = line.split(" contain ")
    if (result(1) == "no other bags.")
      (stripBag(result(0)), List())
    else
      (stripBag(result(0)), detuple(result(1).split(", ").map(stripBag(_).span(_ != ' ')).toList))
  }

  def detuple(list: List[(String, String)]): List[String] = {
    list.map { el =>
      List.fill (el._1.toInt)(el._2.trim)
    }.flatten
  }


  def treeSearchDistinctCount(currBag: String, targetBag: String, bagMap: Map[String, List[String]]): Int = {
    if (currBag == targetBag)
      1
    else {
      //      println(currBag)
      bagMap(currBag).distinct.map(b=>treeSearchDistinctCount(b, targetBag, bagMap)).sum
    }
  }

  def treeSize(currBag: String, bagMap: Map[String, List[String]], depth: Int): Int = {
    println("-".repeat(depth)+currBag)
      1 + bagMap(currBag).map(b=>treeSize(b, bagMap, depth+1)).sum
  }

  def main(args: Array[String]): Unit = {
//    println(allBags.bags.map{b=>println("-"+b.name);treeSearchCount(b, shinyGold)})
    val list = loadList("day01-10/day07/input.txt")
    val bagMap= list.map{ l=>val b = bagLineParse1(l); b._1 -> b._2}.toMap
    println(bagMap)
    val result: Map[String, Int] = bagMap.keys.map{ b=>println(b);b->treeSearchDistinctCount(b, "shiny gold", bagMap)}.toMap
    println(result.count(_._2>0))
    println(treeSize("shiny gold", bagMap, 1))
  }

}
