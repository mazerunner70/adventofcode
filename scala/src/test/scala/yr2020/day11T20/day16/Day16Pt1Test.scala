package yr2020.day11T20.day16

import org.scalatest.flatspec.AnyFlatSpec

class Day16Pt1Test extends AnyFlatSpec {

  val test1 = """class: 1-3 or 5-7
                |row: 6-11 or 33-44
                |seat: 13-40 or 45-50""".stripMargin

  behavior of "Day15Pt1Test"

  it should "doRangeChecker" in {
    assert(Day16Pt1.rangeChecker(List(
      ("class", List(1 to 3, 5 to 7))
    ), 6) == List(("class", List(1 to 3, 5 to 7))))
  }

  it should "parseRules" in {
    assert(Day16Pt1.parseRules(test1.linesIterator.toList) == List (
      ("class", List(1 to 3, 5 to 7)),
      ("row", List(6 to 11, 33 to 44)),
      ("seat", List(13 to 40, 45 to 50) )
    ))
  }

  it should "validateTicket" in {
    assert(Day16Pt1.invalidateTicket(
      List (
        ("class", List(1 to 3, 5 to 7)),
        ("row", List(6 to 11, 33 to 44)),
        ("seat", List(13 to 40, 45 to 50))
      ), List(7,3,47)) == List())
    assert(Day16Pt1.invalidateTicket(
      List (
        ("class", List(1 to 3, 5 to 7)),
        ("row", List(6 to 11, 33 to 44)),
        ("seat", List(13 to 40, 45 to 50))
      ), List(40,4,50)) == List(4))

    assert(Day16Pt1.invalidateTicket(
      List (
        ("class", List(1 to 3, 5 to 7)),
        ("row", List(6 to 11, 33 to 44)),
        ("seat", List(13 to 40, 45 to 50))
      ), List(55,2,20)) == List(55))

    assert(Day16Pt1.invalidateTicket(
      List (
        ("class", List(1 to 3, 5 to 7)),
        ("row", List(6 to 11, 33 to 44)),
        ("seat", List(13 to 40, 45 to 50))
      ), List(38,6,12)) == List(12))

  }

}
