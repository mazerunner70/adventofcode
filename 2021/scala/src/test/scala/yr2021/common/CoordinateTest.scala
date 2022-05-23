package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class CoordinateTest extends AnyFlatSpec {

  behavior of "CoordinateTest"

  it should "isAdjacent" in {
    val coord = Coordinate(0,0)
    assert(coord.isAdjacent(Coordinate(0,0)) == false)
    assert(coord.isAdjacent(Coordinate(1,0)) == true)
    assert(coord.isAdjacent(Coordinate(1,1)) == true)
    assert(coord.isAdjacent(Coordinate(2,1)) == false)
  }

}
