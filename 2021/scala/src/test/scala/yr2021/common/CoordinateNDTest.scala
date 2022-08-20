package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec

class CoordinateNDTest extends AnyFlatSpec {

  it should "sort" in {
    val tar4 = """459,-707,401
                             |-739,-1745,668
                             |-485,-357,347
                             |432,-2009,850
                             |528,-643,409
                             |423,-701,434
                             |-345,-311,381
                             |408,-1815,803
                             |534,-1912,768
                             |-687,-1600,576
                             |-447,-329,318
                             |-635,-1737,486""".stripMargin.split('\n').map(line=> CoordinateND((line.split(',').map(_.toInt)).toVector)).toVector
    assert(tar4.sorted == Vector(CoordinateND(Vector(-739, -1745, 668)), CoordinateND(Vector(-687, -1600, 576)),
      CoordinateND(Vector(-635, -1737, 486)), CoordinateND(Vector(-485, -357, 347)), CoordinateND(Vector(-447, -329, 318)),
      CoordinateND(Vector(-345, -311, 381)), CoordinateND(Vector(408, -1815, 803)), CoordinateND(Vector(423, -701, 434)),
      CoordinateND(Vector(432, -2009, 850)), CoordinateND(Vector(459, -707, 401)), CoordinateND(Vector(528, -643, 409)),
      CoordinateND(Vector(534, -1912, 768))))
  }

}
