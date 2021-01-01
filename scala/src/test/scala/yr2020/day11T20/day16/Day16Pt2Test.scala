package yr2020.day11T20.day16

import org.scalatest.flatspec.AnyFlatSpec
import yr2020.common.Util.multiLineRecordParse
import yr2020.day11T20.day16.Day16Pt1.{parseNearbyTickets, parseRules}

class Day16Pt2Test extends AnyFlatSpec {

  val test1 = """class: 0-1 or 4-19
                |row: 0-5 or 8-19
                |seat: 0-13 or 16-19
                |
                |your ticket:
                |11,12,13
                |
                |nearby tickets:
                |3,9,18
                |15,1,5
                |5,14,9""".stripMargin

  behavior of "Day16Pt2Test"

  it should "candidateRuleList" in {
    assert(Day16Pt2.candidateRuleList(
      List(3, 15, 5),
      List (
        ("class", List(0 to 1, 4 to 9)),
        ("row", List(0 to 5, 8 to 19)),
        ("seat", List(0 to 13, 16 to 19))
      )) == List("row"))
    assert(Day16Pt2.candidateRuleList(
      List(9, 1, 14),
      List (
        ("class", List(0 to 1, 4 to 19)),
        ("row", List(0 to 5, 8 to 19)),
        ("seat", List(0 to 13, 16 to 19))
      )) == List("class", "row"))
    assert(Day16Pt2.candidateRuleList(
      List(18, 5, 9),
      List (
        ("class", List(0 to 1, 4 to 19)),
        ("row", List(0 to 5, 8 to 19)),
        ("seat", List(0 to 13, 16 to 19))
      )) == List("class", "seat", "row"))

  }

  it should "candidateFields" in {
    val sections = multiLineRecordParse(test1.linesIterator.toList)
    val rules = parseRules(sections(0))
    val nearbyTickets = parseNearbyTickets(sections(2).tail)

    assert(Day16Pt2.candidateFields(nearbyTickets, rules) ==
      Map(0 -> List("row"), 1 -> List("class", "row"), 2 -> List("class", "seat", "row"))

    )
  }

  it should "deriveMappings" in {
    val sections = multiLineRecordParse(test1.linesIterator.toList)
    val rules = parseRules(sections(0))
    val nearbyTickets = parseNearbyTickets(sections(2).tail)
    val candidateRules = Day16Pt2.candidateFields(nearbyTickets, rules)
    val mappings = Day16Pt2.deriveMappings(candidateRules, Map())
    assert(mappings == Map(0 -> "row", 1 -> "class", 2 -> "seat"))
  }

}
