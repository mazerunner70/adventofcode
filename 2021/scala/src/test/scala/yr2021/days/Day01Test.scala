package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day01Test extends AnyFlatSpec {

  "doing day 1" should "show a number" in {
    val day01 = new Day01()
    val list = Util.loadList("days/day01/input.txt")
    assert (day01.pt1execute(list) == 1713)
    assert(day01.pt1funct(list) == 1713)
    assert(day01.pt2execute(list) == 1734)

    val list2 = Util.loadList("days/day01/test.txt")
    val intList2 = list2.map(_.toInt)
    assert(day01.compactor(intList2, 3, List.empty) == List(607, 618, 618, 617, 647, 716, 769, 792))
  }



}
