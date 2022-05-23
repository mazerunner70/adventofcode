package fpbook.ch04

import org.scalatest.flatspec.AnyFlatSpec

class Ex02Test extends AnyFlatSpec {

  behavior of "Ex02Test"

  it should "variance" in {
    assert(Ex02.variance(Seq(1, 2, 3)) == Some(2.0/3))
  }

  it should "mean" in {
    assert(Ex02.mean(Seq(1, 2, 3)) == Some(2.0))
  }

}
