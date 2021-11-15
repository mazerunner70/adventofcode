package yr2020.day11T20.day16

import yr2020.common.Util.{loadList, multiLineRecordParse}
import yr2020.day11T20.day16.Day16Pt1.{parseNearbyTickets, parseRules}

import scala.annotation.tailrec
import scala.collection.MapView

object Day16Pt2 {

  def removeInvalidTickets(tickets:List[List[Int]], rangeRules: List[(String, List[Range])]) : List[List[Int]] =
    Day16Pt1.validityChecker(tickets, rangeRules).filter(_._2.size==0).map(_._1)

  def candidateRuleList(fieldValues: List[Int], rangeRules: List[(String, List[Range])]): List[String] = {
    val result1 = fieldValues.map{ fieldValue =>
      Day16Pt1.rangeChecker(rangeRules, fieldValue).map(_._1)
    }.flatten.groupBy(identity).mapValues(_.size).toMap
      result1.filter(_._2 == fieldValues.size).keys.toList
  }



  def candidateFields(tickets:List[List[Int]], rangeRules: List[(String, List[Range])]): Map[Int, List[String]] = {
    val transposed = tickets.transpose
    transposed.zipWithIndex.map (entry => (entry._2 -> candidateRuleList(entry._1, rangeRules))).toMap
  }

  @tailrec
  def deriveMappings(candidates: Map[Int, List[String]], identifiedList: Map[Int, String]): Map[Int, String] = candidates.size match {
    case 0 => identifiedList
    case _ => {
      val identified = candidates.find(_._2.size == 1).get
      val newCandidates = (candidates - identified._1).map(entry =>
        (entry._1 -> (entry._2.filterNot(_ == identified._2(0)))))
      deriveMappings(newCandidates, identifiedList + (identified._1 -> identified._2(0)))
    }
  }

  def calculationTicket(mappings: Map[Int, String], myTicket: List[Int]): Long = {
    val sixFields = mappings.filter(_._2.startsWith("departure")).map(entry=> myTicket(entry._1).toLong).toList
    sixFields.product
  }


  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day16/input.txt")
    val sections = multiLineRecordParse(list)
    val rules = parseRules(sections(0))
    val myTicket = parseNearbyTickets(sections(1).tail)(0)
    val nearbyTickets = parseNearbyTickets(sections(2).tail)
    val goodTickets = removeInvalidTickets(nearbyTickets, rules)
    val candidateRules = Day16Pt2.candidateFields(goodTickets, rules)
    val mappings = Day16Pt2.deriveMappings(candidateRules, Map())
    println(mappings)
    println(calculationTicket(mappings, myTicket))

  }

}
