package fpbook.ch02

import org.scalatest.flatspec.AnyFlatSpec

class Ex01Test extends AnyFlatSpec {

  it should "get fibonacci" in {
    val result = Ex01.fibonacci(6)
    assert (result == 8)
    assert(Ex01.fibonacci(0) == 0)
    assert(Ex01.fibonacci(1) == 1)
    assert(Ex01.fibonacci(2) == 1)
    assert(Ex01.fibonacci(3) == 2)
    assert(Ex01.fibonacci(4) == 3)
  }

}
