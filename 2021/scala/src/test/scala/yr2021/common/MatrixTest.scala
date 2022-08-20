package yr2021.common

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.days.Day19

class MatrixTest extends AnyFlatSpec {

  behavior of "MatrixTest"

  it should "parse" in {
    assert(Matrix.parse(Vector(Vector(12, 12))) == Some(Matrix(Vector(Vector(12, 12)))))
    assert(Matrix.parse(Vector(Vector(1, 2), Vector(3))) == None)
  }

  it should "parse2" in {
    assert(Matrix.parse(CoordinateND(Vector(2, 4, 67))) == Matrix(Vector(Vector(2, 4, 67))))
  }

  it should "scalar *" in {
    assert(Matrix.parse(Vector(Vector(12, 12))).get * 5 == Matrix.parse(Vector(Vector(60, 60))).get )
  }

  it should "matrix *" in {
    assert(Matrix.parse(Vector(Vector(12, 12))).get * Matrix.parse(Vector(Vector(12, 12))).get == None )
    assert(Matrix.parse(Vector(Vector(12, 12))).get * Matrix.parse(Vector(Vector(12), Vector(12))).get == Some(Matrix(Vector(Vector(288)))) )
    assert( Matrix.parse(Vector(Vector(12), Vector(12))).get * Matrix.parse(Vector(Vector(12, 12))).get== Some(Matrix(Vector(Vector(144, 144), Vector(144, 144)))) )
  }

  it should "rotations-z" in {
    val r0 = Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 1)))
    val r90 = Matrix.parse(Vector(Vector(0, -1, 0), Vector(1,0,0), Vector(0,0,1))).get
    val r180 = (r90 * r90).get
    val r270 = (r180 * r90).get
    val r360 = (r270 * r90).get
    assert (r0 == r360)
  }

  def rotateTest(transform: Matrix): Boolean = {
    val r0 = Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 1)))
    (List.fill(4)(transform)).reduceLeft((x, y) => (x * y).get) == r0
  }

  it should "all variations" in {
    val identity = Matrix(Vector(Vector(1,  0, 0), Vector(0, 1,  0), Vector( 0, 0, 1)))
    val rotateZ =  Matrix(Vector(Vector(0, -1, 0), Vector(1, 0,  0), Vector( 0, 0, 1)))
    val rotateY =  Matrix(Vector(Vector(0,  0, 1), Vector(0, 1,  0), Vector(-1, 0, 0)))
    val rotateX =  Matrix(Vector(Vector(1,  0, 0), Vector(0, 0, -1), Vector( 0, 1, 0)))
    assert(rotateTest(rotateZ))
    assert(rotateTest(rotateY))
    assert(rotateTest(rotateX))
  }

  it should "toCoordinate" in {
    assert(Matrix(Vector(Vector(1,  0, 0), Vector(0, 1,  0), Vector( 0, 0, 1))).toCoordinates ==
      Vector(CoordinateND(Vector(1, 0, 0)), CoordinateND(Vector(0, 1, 0)), CoordinateND(Vector(0, 0, 1))))
  }

  it should "resize" in {
    val matrix = Matrix(Vector(Vector(1,  0, 0), Vector(0, 1,  0), Vector( 0, 0, 1)))
    assert(matrix.resize(1, 1) == Matrix(Vector(Vector(1))))
    assert(matrix.resize(1, 2) == Matrix(Vector(Vector(1, 0))))
    assert(matrix.resize(3, 2) == Matrix(Vector(Vector(1, 0), Vector(0, 1), Vector(0, 0))))
    assert(matrix.resize(3, 3) == Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 1))))
    assert(matrix.resize(4, 3) == Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 1), Vector(0, 0, 0))))
    assert(matrix.resize(4, 4) == Matrix(Vector(Vector(1, 0, 0, 0), Vector(0, 1, 0, 0), Vector(0, 0, 1, 0), Vector(0, 0, 0, 0))) )
    assert(matrix.resize(5, 5) == Matrix(Vector(Vector(1, 0, 0, 0, 0), Vector(0, 1, 0, 0, 0), Vector(0, 0, 1, 0, 0),
      Vector(0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0))))
  }

  it should "set" in {
    val matrix = Matrix(Vector(Vector(1,  0, 0), Vector(0, 1,  0), Vector( 0, 0, 1)))
    assert(matrix.updated(2, 2, 6) == Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 6))))
  }

}
