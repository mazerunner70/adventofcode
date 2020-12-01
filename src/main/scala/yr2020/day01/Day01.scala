package yr2020.day01

import scala.io.Source

object Day01 {


  def iterate2(array: Array[Int]): Int = {
    for (item <- array) {
      if (array.contains(2020 - item))
        return item
    }
    -1
  }

  def iterate3(array: Array[Int]): (Int, Int) = {
    for (item <- array) {
      for (item2 <- array) {
        if (array.contains(2020 - item - item2))
          return (item, item2)
      }
    }
    (-1, 1)
  }

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def main(args: Array[String]): Unit = {
    val pageContent = loadFromFile("day01/input.txt")
    val array = pageContent.split('\n').map(_.toInt).sorted
    val foundvalue2 = iterate2(array)
    println(foundvalue2 * (2020 - foundvalue2))
    val foundvalue3 = iterate3(array)
    println(foundvalue3._1 * foundvalue3._2 * (2020 - foundvalue3._1 - foundvalue3._2))
  }


}
