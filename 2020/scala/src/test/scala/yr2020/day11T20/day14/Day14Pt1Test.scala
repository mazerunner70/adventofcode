package yr2020.day11T20.day14

import org.scalatest.flatspec.AnyFlatSpec

import yr2020.day11T20._

class Day14Pt1Test extends AnyFlatSpec {

  behavior of "Day14Pt1Test"

  val test1 = """mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                |mem[8] = 11
                |mem[7] = 101
                |mem[8] = 0""".stripMargin

  it should "executeLines" in {
    val result = Day14Pt1.getResult(
      executeLines(test1.split("\n").toList, Map(), "", Day14Pt1.operations)._1
    )
    assert(165 == result)
  }

}
