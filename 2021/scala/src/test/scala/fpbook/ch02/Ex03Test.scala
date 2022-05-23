package fpbook.ch02

import org.scalatest.flatspec.AnyFlatSpec

class Ex03Test extends AnyFlatSpec {

  it should "partial" in {
    val partialAdd = Ex03.partial1(5, (a:Int, b: Int)=> a + b)
    assert(partialAdd(6) == 11)
  }

  it should "curry" in {
    val curried = Ex03.curry((a:Int, b: Int)=> a + b)
    assert(curried(5)(6) == 11)
  }



}
