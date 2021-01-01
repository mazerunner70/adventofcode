package yr2020.day11T20.day14

import org.scalatest.flatspec.AnyFlatSpec

class Day14Pt2Test extends AnyFlatSpec {

  val test1 = """mask = 000000000000000000000000000000X1001X
                |mem[42] = 100""".stripMargin

  val test2 = """mask = 000000000000000000000000000000X1001X
                |mem[42] = 100
                |mask = 00000000000000000000000000000000X0XX
                |mem[26] = 1""".stripMargin

  behavior of "Day14Pt2Test"

  it should "getAllAddresses" in {
    val addressList = Day14Pt2.getAllAddresses("000000000000000000000000000000X1001X")
    assert(addressList.map(_.toBinaryString) == List("10010", "10011", "110010", "110011", "110010"))
  }

  it should "executeLines" in {
    val result = executeLines(test1.split("\n").toList, Map(), "", Day14Pt2.operations)._1
    assert(result == Map(26 -> 100, 27 -> 100, 58 -> 100, 59 -> 100))
  }

  it should "getResult" in {
    val result = Day14Pt1.getResult(
      executeLines(test2.split("\n").toList, Map(), "", Day14Pt2.operations)._1
    )
    println(result)
  }

}
