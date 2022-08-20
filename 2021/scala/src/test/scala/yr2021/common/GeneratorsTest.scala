package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class GeneratorsTest extends AnyFlatSpec {

  it should "keep +ve" in {
    val sd = SimpleDeterministic(-3, 1, 2)
    val r1 = sd.nextInt
    assert(r1._1 == -2)
    val nn = Generators.nonNegativeInt(sd)
    assert(nn._1 == 2147483646)
  }
}
