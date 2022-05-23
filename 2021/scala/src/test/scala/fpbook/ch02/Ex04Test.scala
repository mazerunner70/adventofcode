package fpbook.ch02

import org.scalatest.flatspec.AnyFlatSpec

class Ex04Test extends AnyFlatSpec {

  it should "uncurry" in {
    val curried = (a:Int)=>(b:Int)=>a * b
    val uncurried = Ex04.uncurry(curried)
    assert(uncurried(5, 6) == 30)
  }

}
