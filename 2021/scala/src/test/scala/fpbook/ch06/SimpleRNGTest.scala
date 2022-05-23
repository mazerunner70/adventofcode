package fpbook.ch06

import org.scalatest.flatspec.AnyFlatSpec

class SimpleRNGTest extends AnyFlatSpec {

  behavior of "SimpleRNGTest"

  it should "ints" in {
    val rng = SimpleRNG(5)
    assert(RNG.ints(3)(rng) == (List(832832900, 1478223345, 1923744),SimpleRNG(54580536946886L)))
  }
  it should "double" in {
    val rng = SimpleRNG(5)
    assert(RNG.double(rng) == (8.958131074905396E-4,SimpleRNG(126074519596L)))
  }
  it should "double2" in {
    val rng = SimpleRNG(5)
    assert(RNG.double2(rng) == (8.958131074905396E-4,SimpleRNG(126074519596L)))
  }
  it should "map2" in {
    val rng = SimpleRNG(5)
    assert(RNG.map2(RNG.double2, RNG.nonNegativeInt)((d, i)=>(i, d))(rng) == ((1478223345,8.958131074905396E-4),SimpleRNG(184598131514055L)))
  }

}
