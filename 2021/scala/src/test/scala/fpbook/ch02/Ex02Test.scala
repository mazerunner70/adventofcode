package fpbook.ch02

import org.scalatest.flatspec.AnyFlatSpec

class Ex02Test extends AnyFlatSpec {

  it should "show sorted state" in {
    assert(Ex02.isSorted(Seq(0, 1, 2, 3,4), (a:Int, b:Int)=>a < b) == true)
    assert(Ex02.isSorted(Seq(0, 1, 6, 3,4), (a:Int, b:Int)=>a < b) == false)
  }

}
