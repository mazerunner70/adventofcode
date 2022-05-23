package fpbook.ch03

import org.scalatest.flatspec.AnyFlatSpec

class Ex07Test extends AnyFlatSpec {

  behavior of "Ex07Test"

  it should "foldLeft" in {
    assert(Ex07.foldLeft(List(1, 2, 3), 0)((a, e)=>a+e) == 6)
  }

}
