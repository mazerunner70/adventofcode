package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day10Test extends AnyFlatSpec {

  behavior of "Day10Test"

  it should "braceParser" in {
    val day10 = new Day10
    assert(day10.braceParser(raw"([])".toList, List()) == day10.Result(0,List()))
    assert(day10.braceParser(raw"{()()()}".toList, List()) == day10.Result(0,List()))
    assert(day10.braceParser(raw"<([{}])>".toList, List()) == day10.Result(0,List()))
    assert(day10.braceParser(raw"[<>({}){}[([])<>]]".toList, List()) == day10.Result(0,List()))
    assert(day10.braceParser(raw"(((((((((())))))))))".toList, List()) == day10.Result(0,List()))

    assert(day10.braceParser(raw"(]".toList, List()) == day10.Result(']',List(day10.BracePair('(',')'))))
    assert(day10.braceParser(raw"{()()()>".toList, List()) == day10.Result('>',List(day10.BracePair('{','}'))))
    assert(day10.braceParser(raw"(((()))}".toList, List()) == day10.Result('}',List(day10.BracePair('(',')'))))
    assert(day10.braceParser(raw"<([]){()}[{}])".toList, List()) == day10.Result(')',List(day10.BracePair('<','>'))))
  }

  it should "pt1-test" in {
    val day10 = new Day10
    val testLines = loadList("days/day10/test.txt")
    assert(day10.pt1(testLines) == 26397)
  }
  it should "pt1" in {
    val day10 = new Day10
    val testLines = loadList("days/day10/input.txt")
    assert(day10.pt1(testLines) == 271245)
  }

  it should "pt2-test" in {
    val day10 = new Day10
    val testLines = loadList("days/day10/test.txt")
    assert(day10.pt2(testLines) == 288957)
  }

  it should "pt2" in {
    val day10 = new Day10
    val testLines = loadList("days/day10/input.txt")
    assert(day10.pt2(testLines) == 288957)
  }


}
