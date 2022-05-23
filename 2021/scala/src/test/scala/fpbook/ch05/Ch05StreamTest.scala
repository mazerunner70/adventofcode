package fpbook.ch05

import org.scalatest.flatspec.AnyFlatSpec

class Ch05StreamTest extends AnyFlatSpec {

  behavior of "Ch05StreamTest"

  it should "headOption" in {
    assert(Ch05Stream(1, 2, 3).headOption == Some(1))
  }
  it should "toList" in {
    assert(Ch05Stream(1, 2, 3).toList == List(1, 2, 3))
  }
  it should "take" in {
    assert(Ch05Stream(1, 2, 3).take(2) == List(1, 2))
  }
  it should "drop" in {
    assert(Ch05Stream(1, 2, 3).drop(2).toList == List(3))
  }
  it should "takeWhile" in {
    assert(Ch05Stream(1, 2, 3).takeWhile(x=>x<2) == List(1))
  }
  it should "forAll" in {
    assert(Ch05Stream(1, 2, 3).forAll(x=>x<2) == false)
  }
  it should "foldedMap" in {
    assert(Ch05Stream(1, 2, 3).foldedMap(x=>x*2).toList == List(2, 4, 6))
  }
  it should "foldedFilter" in {
    assert(Ch05Stream(1, 2, 3).foldedFilter(x=>x<2).toList == List(1))
  }
  it should "constant" in {
    assert(Ch05Stream.constant(2).take(5).toList == List(2, 2, 2, 2, 2))
  }
  it should "from" in {
    assert(Ch05Stream.from(2).take(5).toList == List(2, 3, 4, 5, 6))
  }
  it should "fibs" in {
    assert(Ch05Stream.fibs.take(5).toList == List(0, 1, 1, 2, 3))
  }


}
