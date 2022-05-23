package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day16Test extends AnyFlatSpec {

  behavior of "Day16Test"

  it should "Hex2RevBinary" in {
    assert(Day16.Hex2Binary('a') == "1010")
  }

  it should "parseLine" in {
    assert(Day16.parseLine("ab") == "10101011")
  }

  it should "unit" in {
    val unit = Day16.unit('C')
    assert(unit(List('V')) == ('C',List('V')))
  }

  it should "chars" in {
    assert(Day16._chars(2)(List('A', 'B', 'C')) == (List('A', 'B'), List('C')))
  }

  it should "sequence" in {
    assert(Day16.sequence(List(Day16._chars(2), Day16._chars(2)))(List('A', 'B', 'C','D', 'E', 'F')) == (List(List('A', 'B'), List('C', 'D')),List('E', 'F')))
  }

//  it should "repeatWhile" in {
//    assert(Day16.repeatWhile(2, (x=>x(0) == 'A'), List('A', 'B', 'A', 'C', 'B', 'E')) == List(List('A', 'B'), List('A', 'C')))
//  }
}
