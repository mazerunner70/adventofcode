package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day02Test extends AnyFlatSpec {

  "doing day 2" should "show assert the answers" in {
    val day02 = new Day02()
    val testList = Util.loadList("days/day02/test.txt")
    assert(day02.pt1(testList) == 150)

    val list = Util.loadList("days/day02/input.txt")
    assert(day02.pt1(list) == 1938402)

    //pt 2
    assert(day02.pt2(testList) == 900)
    assert(day02.pt2(list) == 1938402)

  }


}
