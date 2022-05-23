package yr2021.days.fppractice

import org.scalatest.flatspec.AnyFlatSpec

class Day16FPPracticeTest extends AnyFlatSpec {

  it should "testprinting" in {
    val day16FPPractice = new Day16FPPractice
    assert(day16FPPractice.printIntField1(6) == "Value is 6")
    assert(day16FPPractice.printIntField2(8) == "Value is 8")
  }

}
