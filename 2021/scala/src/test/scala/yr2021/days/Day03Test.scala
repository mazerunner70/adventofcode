package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day03Test extends AnyFlatSpec {

  "doing day 3 transpose" should "show assert the answers" in {
    val list = List("aba", "abb")
    assert(list.transpose == List(List('a', 'a'), List('b', 'b'), List('a', 'b')))
    assert(list.transpose.map(x => x.groupBy(_.toString)) == List(Map("a" -> List('a', 'a')), Map("b" -> List('b', 'b')), Map("a" -> List('a'), "b" -> List('b'))))
    assert(list.transpose.map(x => x.groupBy(_.toString).maxBy(_._2.size)) == List(("a", List('a', 'a')), ("b", List('b', 'b')), ("a", List('a'))))
    assert(list.transpose.map(x => x.groupBy(_.toString).maxBy(_._2.size)._1) == List("a", "b", "a"))
    assert(list.transpose.map(x => x.groupBy(_.toString).maxBy(_._2.size)._1).mkString == "aba")
  }

  "doing day 3 pt1" should "show assert the answers" in {
    val day03 = new Day03()
    val testList = Util.loadList("days/day03/test.txt")
    assert(day03.pt1(testList) == 198)
    val list = Util.loadList("days/day03/input.txt")
    assert(day03.pt1(list) == 1025636)
  }
  "doing day 3 pt2" should "show assert the answers" in {
    val day03 = new Day03()
    val testList = Util.loadList("days/day03/test.txt")
    val list = Util.loadList("days/day03/input.txt")
    assert(day03.pt2(testList) == 230)
    assert(day03.pt2(list) == 793873)

  }
}