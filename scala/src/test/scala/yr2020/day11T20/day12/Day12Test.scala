package yr2020.day11T20.day12

import org.scalatest.flatspec.AnyFlatSpec

import scala.math.{atan, atan2, cos, sin, sqrt}

class Day12Test extends AnyFlatSpec {

  behavior of "Day12Test"

  def baseTest(x: Int, y: Int, degrees: Int): (Double, Double) =  {
    val radians = -degrees.toRadians
    val angle = atan2(y, x)
    val mag = sqrt(x*x+y*y)
    ( cos(angle+radians)*mag, sin(angle+radians)*mag )

  }

  "turn right 0" should "leave waypoint unchanged" in {
    val waypoint = Day12.rules2("R")(5, 6, 0, (0,0))
    assert(waypoint == (5, 6, 0, (0,0)))
  }

  "turn right 90" should "move waypoint to right" in {
    val (x, y, degrees) = (6, 5, 90)
    val (newX, newY) = baseTest(x, y, degrees)
    val waypoint = Day12.rules2("R")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
  }

  "turn right 180" should "move waypoint to reverse" in {
    val (x, y, degrees) = (6, 5, 180)
    val (newX, newY) = baseTest(x, y, degrees)
    val waypoint = Day12.rules2("R")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
  }

  "turn right 270" should "move waypoint to left" in {
    val (x, y, degrees) = (6, 5, 270)
    val (newX, newY) = baseTest(x, y, degrees)
    val waypoint = Day12.rules2("R")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
    println(newX,newY)
    assert((Day12.rules2("R")(2, -22, 270, (0,0))) == (22, 2, 270,(0,0)))
  }

  "turn left 0" should "move waypoint to left" in {
    val (x, y, degrees) = (6, 5, 0)
    val (newX, newY) = baseTest(x, y, degrees)
    val waypoint = Day12.rules2("L")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
  }

  "turn left 90" should "move waypoint to left" in {
    val (x, y, degrees) = (6, 5, 90)
    val (newX, newY) = baseTest(x, y, -degrees)
    val waypoint = Day12.rules2("L")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
  }

  "turn left 180" should "move waypoint to left" in {
    val (x, y, degrees) = (6, 5, 180)
    val (newX, newY) = baseTest(x, y, -degrees)
    val waypoint = Day12.rules2("L")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
  }

  "turn left 270" should "move waypoint to left" in {
    val (x, y, degrees) = (6, 5, 270)
    val (newX, newY) = baseTest(x, y, -degrees)
    val waypoint = Day12.rules2("L")(x, y, degrees, (0,0))
    assert(waypoint == (newX.round, newY.round, degrees, (0,0)))
  }

}
