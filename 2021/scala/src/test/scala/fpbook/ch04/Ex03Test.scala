package fpbook.ch04

import org.scalatest.flatspec.AnyFlatSpec

class Ex03Test extends AnyFlatSpec {

  behavior of "Ex03Test"

  it should "map2" in {
    val none: Option[Int] = None
    assert(Ex03.map2(Some(1), Some(2)) ((a, b)=>a + b) == Some(3))
    assert(Ex03.map2(none, Some(2)) ((a, b)=>a + b) == None)
    assert(Ex03.map2(Some(1), none) ((a, b)=>a + b) == None)
  }

}
