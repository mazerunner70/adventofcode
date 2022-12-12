package yr2022.days

import yr2022.common.InputData
import yr2022.days.Day01.CalorieList

class Day01Test extends org.scalatest.flatspec.AnyFlatSpec {

  it should "do test1" in {
    val inputData = InputData.fromFile("days/day01/test.txt")
    assert(Day01.parseFile(inputData).size == 5)
    assert(Day01.parseFile(inputData) == List(CalorieList(List("1000", "2000", "3000")), CalorieList(List("4000")), CalorieList(List("5000", "6000")), CalorieList(List("7000", "8000", "9000")), CalorieList(List("10000"))))
  }

  it should "max" in {
    val inputData = InputData.fromFile("days/day01/test.txt")
    val m = Day01.pt1(inputData)
    assert(m == 24000)
  }

  it should "pt1" in {
    val inputData = InputData.fromFile("days/day01/input.txt")
    val m = Day01.pt1(inputData)
    assert(m == 71934)
  }

  it should "pt2" in {
    val inputData = InputData.fromFile("days/day01/input.txt")
    val m = Day01.pt2(inputData)
    assert(m == 71934)
  }

}
