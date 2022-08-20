package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class Coordinate2DTest extends AnyFlatSpec {

  behavior of "CoordinateTest"

  it should "isAdjacent" in {
    val coord = Coordinate2D(0,0)
    assert(coord.isAdjacent(Coordinate2D(0,0)) == false)
    assert(coord.isAdjacent(Coordinate2D(1,0)) == true)
    assert(coord.isAdjacent(Coordinate2D(1,1)) == true)
    assert(coord.isAdjacent(Coordinate2D(2,1)) == false)
  }

}
