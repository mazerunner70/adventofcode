package fpbook.ch06

import org.scalatest.flatspec.AnyFlatSpec

class StateTest extends AnyFlatSpec {

  behavior of "StateTest"

  it should "unit" in {
    val state = State.unit(Machine(false, 2, 2));
    val result = state.run
    val res = State.get
  }

}
