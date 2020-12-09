package yr2020.day04

import yr2020.common.Util.loadList

import scala.annotation.tailrec
import scala.util.matching.Regex
object Day04 {

  case class Validation(regex: Regex, algo: (String) => Boolean)

  val rules = List(
    Validation("byr:(\\d{4,4})$".r,       checkRange(1920, 2002)),
    Validation("iyr:(\\d{4,4})$".r,       checkRange(2010, 2020)),
    Validation("eyr:(\\d{4,4})$".r,       checkRange(2020, 2030)),
    Validation("hgt:(\\d{2,2})in$".r,     checkRange(59, 76)),
    Validation("hgt:(\\d{3,3})cm$".r,     checkRange(150, 193)),
    Validation("hcl:#([0-9a-f]{6,6})$".r, noCheck()),
    Validation("ecl:([a-z]{3,3})$".r,     checkEnum(List("amb", "blu", "brn", "gry", "grn", "hzl", "oth"))),
    Validation("pid:([0-9]{9,9})$".r,     noCheck()),
    Validation("cid:(.*)$".r,             noCheck())
  )

  def checkEnum(allowed: List[String] ): (String) => Boolean = {
    def check(string: String) =
      allowed.contains(string)
    check
  }

  def checkRange(lower: Int, upper: Int): (String) => Boolean = {
    def check(string: String) =
      lower to upper contains(string.toInt)
    check
  }
  def noCheck(): (String) => Boolean = {
    def check(string: String) =
      true
    check
  }

  def doCheck(string: String): Boolean = {
    val results = rules.map(r=> r.regex.findFirstMatchIn(string) match {
      case Some(pm) => r.algo(pm.group(1))
      case None     => false
    })
    results.filter(_ == true).size == 1
  }

  def validateRecord2(record: List[String]) = {
    val compactRecord = record.mkString(" ")
    println(compactRecord)
    val matches = compactRecord.split(" ").map { f =>
      doCheck(f)
    }
//    println(matches.toList)
    matches.filter(_ != true).size == 0
  }

  def validateRecord1(record: List[String]) = {
    val compactRecord = record.mkString(" ")
    val mandatory = List("byr", "iyr" , "eyr" , "hgt" , "hcl", "ecl" , "pid")
    val optional = List("cid")
    val matches = compactRecord.split(" ").map{ f=>
      f.substring(0,3) match {
        case str if (mandatory.contains(str)) => str
        case str if (optional.contains((str))) => null
        case _ => throw new Exception()
      }
    }.filter(_ != null).toList
    matches.size == 7
  }

  def iterate(list:List[String]): List[List[String]] = {
    val (tail, record) = nextRecord(list, List())
    tail match {
      case Nil => List(record)
      case _   => record :: iterate(tail)
    }
  }

  @tailrec
  def nextRecord(sourceList:List[String], record: List[String]): (List[String], List[String]) = sourceList match {
    case h :: Nil => (Nil, h :: record)
    case h :: tail if h.size > 0 => nextRecord(tail, h :: record)
    case h :: tail if h.size == 0 => (tail, record)
  }


  def main(args: Array[String]): Unit = {
    val list = loadList("day04/input.txt")
    val records = iterate(list)
    val validCount1 = records.map (validateRecord1(_)).filter(_ == true).size
    println(validCount1)
    val validCount2 = records.map (p=>validateRecord1(p) && validateRecord2(p)).filter(_ == true).size
    println(validCount2)

  }

}
