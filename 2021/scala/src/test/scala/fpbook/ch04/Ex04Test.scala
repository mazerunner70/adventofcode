package fpbook.ch04

import org.scalatest.flatspec.AnyFlatSpec

class Ex04Test extends AnyFlatSpec {

  it should "sequence" in {
    assert(Ex04.sequence(List(Some(3), Some(6), Some(9))) == Some(List(3, 6, 9)))
    assert(Ex04.sequence(List(Some(3), None, Some(6), Some(9))) == None)
  }

}
