package yr2020.day01T10.day02

import scala.io.Source
import scala.util.matching.Regex

object Day02 {

  def loadFromFile(filename: String): String =
    Source.fromResource(filename).mkString

  def parseLine(line: String): (Int, Int, Char, String) = {
    val pattern: Regex = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".r
     pattern.findFirstMatchIn(line) match {
       case Some(regexMatch) => return (regexMatch.group(1).toInt ,regexMatch.group(2).toInt ,regexMatch.group(3).charAt(0) ,regexMatch.group(4))
       case None => println(line)
     }
    (0,0, '0',"")
  }

  def validate1Line(lowCount: Int, highCount: Int, letter: Char, candidate: String): Boolean = {
    val count = candidate.count(_ == letter)
    lowCount to highCount contains(count)
  }

  def validate2Line(lowPosition: Int, highPosition: Int, letter: Char, candidate: String): Boolean = {
    (candidate.charAt(lowPosition-1) == letter) ^ candidate.charAt(highPosition-1) == letter
  }



  def main(args: Array[String]): Unit = {
    val pageContent = loadFromFile("day01-10/day02/input.txt")
    val list = pageContent.split('\n').map(_.toString).toList
    println(
      list.count { line: String =>
        val result = parseLine(line)
        validate1Line(result._1, result._2, result._3, result._4)
      }
    )
    println(
      list.count { line: String =>
        val result = parseLine(line)
        validate2Line(result._1, result._2, result._3, result._4)
      }
    )
  }

}
