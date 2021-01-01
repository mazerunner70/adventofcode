package yr2020.day11T20.day13

import org.scalatest.flatspec.AnyFlatSpec

class Day13P2v2Test extends AnyFlatSpec {

  behavior of "Day13P2v2Test"

  it should "crt" in {
    val sourceData = List((3, 2),(4, 2),(5, 1))
    val result = Day13P2v2.crt(sourceData)
    println(result)
  }

}
