package yr2020.day03

import scala.io.Source

object Day03 {

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def traverse(grid: List[String], xInc: Int, yInc: Int) :Int = {
    val gridWidth = grid(0).size
    def move(x: Int, y: Int, treeCount:Int): Int = {
      if (y<grid.size)
        move(x+xInc, y+yInc, treeCount + (if (grid(y).charAt(x % gridWidth) == '#') 1 else 0 ))
      else
        treeCount
    }
    move(0, 0, 0)
  }

  def traverseGroup(grid: List[String], slopes: List[(Int, Int)], counts: List[Long]): List[Long] = slopes match {
    case h :: Nil => traverse(grid, h._1, h._2):: counts
    case h :: tail => traverseGroup(grid, tail, traverse(grid, h._1, h._2):: counts)
  }

  def main(args: Array[String]): Unit = {
    val pageContent = loadFromFile("day03/input.txt")
    val list = pageContent.split('\n').map(_.toString).toList
    println(traverse(list, 3, 1))
    val tg = traverseGroup(list, List(1-> 1, 3 -> 1,  5 -> 1, 7 -> 1, 1 -> 2), List())
    println(tg)
    println(tg.product)
  }

}
