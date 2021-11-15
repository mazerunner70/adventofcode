package yr2020.day11T20.day16

import yr2020.common.Util.{loadList, multiLineRecordParse}

object Day16Pt1 {



  def rangeChecker(rangeRules: List[(String, List[Range])], value: Int): List[(String, List[Range])] = {
    rangeRules.filter(rule=> {
      rule._2.exists(_ contains value)
    })
  }

  def invalidateTicket(rangeRules: List[(String, List[Range])], ticketFields: List[Int]): List[Int] = {
    ticketFields.filter(rangeChecker(rangeRules, _).size == 0)
  }

  def parseNearbyTickets(lines: List[String]):List[List[Int]] = {
    lines.map(_.split(',').map(_.toInt).toList)
  }

  def doTests(lines: List[String]): List[(List[Int], List[Int])] = {
    val sections = multiLineRecordParse(lines)
    val rules = parseRules(sections(0))
    val nearbyTickets = parseNearbyTickets(sections(2).tail)
    validityChecker(nearbyTickets, rules)
  }

  def parseRules(lines: List[String]): List[(String, List[Range])] = {
    val assign = raw"""([a-z ]+): (\d+)-(\d+) or (\d+)-(\d+)""".r
    lines.map {line =>
      line match {
        case assign(name, low1, high1, low2, high2) => (name, List(low1.toInt to high1.toInt, low2.toInt to high2.toInt))
      }
    }
  }
  // output tuple (row, list of invalid fields)
  def validityChecker(tickets:List[List[Int]], rangeRules: List[(String, List[Range])]): List[(List[Int], List[Int])] = {
    tickets.map {ticket =>
      (ticket, invalidateTicket(rangeRules, ticket))
    }
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day16/input.txt")
    val results = doTests(list)
    println(results)
    println(results.flatMap(_._2).sum)
  }

}
