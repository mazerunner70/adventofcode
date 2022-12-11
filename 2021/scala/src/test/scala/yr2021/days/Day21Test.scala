package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day21Test extends AnyFlatSpec {

  it should "turn" in {

  }

  it should "test" in {
    assert(Day21.start(Vector(4, 8))  == 739785)
  }

  it should "part1" in {
    assert(Day21.start(Vector(7, 2))  == 678468)
  }

  it should "part2" in {
    assert(Day21.play2End(4, 8, 0, 0) == (341960390180808L,444356092776315L))
    assert(Day21.play2End(7, 2, 0, 0) == ((123918341809156L,131180774190079L)))
  }

}
