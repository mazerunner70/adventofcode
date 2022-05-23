package fpbook2.ch02

import org.scalatest.flatspec.AnyFlatSpec

class Ch02Test extends AnyFlatSpec {

  behavior of "Ch02Test"

  it should "fib" in {
    assert(Ch02.fib(0) == 0)
    assert(Ch02.fib(1) == 1)
    assert(Ch02.fib(2) == 1)
    assert(Ch02.fib(3) == 2)
    assert(Ch02.fib(4) == 3)
    assert(Ch02.fib(5) == 5)
  }

  it should "isSorted" in {
    assert(Ch02.isSorted(Array(0, 1, 2), (x:Int, y:Int)=>x <= y) == true)
    assert(Ch02.isSorted(Array(0, 1, 2, 2), (x:Int, y:Int)=>x <= y) == true)
    assert(Ch02.isSorted(Array(0, 1, 3, 2), (x:Int, y:Int)=>x <= y) == false)
  }

  it should "curry" in {
    val g = Ch02.curry((a: Int, b: Int)=>a+b)(5)
    assert(g(6) == 11)
  }



}
