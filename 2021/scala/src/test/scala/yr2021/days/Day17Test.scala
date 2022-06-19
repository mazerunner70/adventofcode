package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.days.Day17._

class Day17Test extends AnyFlatSpec {

  it should "inside coord" in {
    val targetArea = Box(Coordinate(20, -10), Coordinate(30, -5))
    assert( targetArea.inside(Coordinate(0,0)) == false )
  }
  it should "inside box" in {
    val targetArea = Box(Coordinate(20, -10), Coordinate(30, -5))
    assert( targetArea.inside(Box(Coordinate(0,0), Coordinate(0,0))) == false)
  }
  it should "beyond" in {
    val targetArea = Box(Coordinate(20, -10), Coordinate(30, -5))
     assert(Day17.beyondTarget(Coordinate(0,0), Velocity(7,2), targetArea) == false)
  }

  it should "trajectory" in {
    val targetArea = Box(Coordinate(20, -10), Coordinate(30, -5))
    val traj = Day17.trajectory(Coordinate(0,0), Velocity(7, 2), targetArea)
    assert(traj == List(Coordinate(0,0), Coordinate(7,2), Coordinate(13,3), Coordinate(18,3),
      Coordinate(22,2), Coordinate(25,0), Coordinate(27,-3), Coordinate(28,-7)))
  }

  it should "launchProbe" in {
    val targetArea = Box(Coordinate(20, -10), Coordinate(30, -5))
    assert(Day17.launchProbe(Coordinate(0,0), Velocity(7, 2), targetArea) == ProbeOutcome(true, 3,Coordinate(28,-7),0))
    assert(Day17.launchProbe(Coordinate(0,0), Velocity(6, 3), targetArea) == ProbeOutcome(true, 6,Coordinate(21,-9),0))
    assert(Day17.launchProbe(Coordinate(0,0), Velocity(9, 0), targetArea) == ProbeOutcome(true, 0,Coordinate(30,-6),0))
    assert(Day17.launchProbe(Coordinate(0,0), Velocity(17, -4), targetArea) == ProbeOutcome(false, 0,Coordinate(48,-15),1))
    assert(Day17.launchProbe(Coordinate(0,0), Velocity(15,-2), targetArea) == ProbeOutcome(true, 0,Coordinate(29,-5),0))
    assert(Day17.launchProbe(Coordinate(0,0), Velocity(20,-10), targetArea) == ProbeOutcome(true, 0,Coordinate(20,-10),0))
  }

  it should "tryFindHeights" in {
    val targetArea = Box(Coordinate(20, -10), Coordinate(30, -5))
    val start = Coordinate(0,0)
    assert(Day17.tryFindHeights(7, start, targetArea) == 45)
    assert(Day17.tryFindHeights(6, start, targetArea) == 45)
    assert(Day17.tryFindHeights(5, start, targetArea) == 0)
  }

  it should "iterateX" in {
    val tests = List((Box(Coordinate(20, -10), Coordinate(30, -5)), 45, 112),
      (Box(Coordinate(236, -78), Coordinate(262, -58)), 3003, 400))
    tests.foreach { x =>
      val targetArea = x._1
      val start = Coordinate(0, 0)
      val (maxY, successList) = Day17.iterateX(start, targetArea)
      assert(maxY == x._2)
      assert(successList.size == x._3)
    }

  }
  it should "iterateXPt2" in {
    def asVelocity(text: String): Velocity = {
      val value = text.split(',').map(_.toInt)
      Velocity(value(0), value(1))
    }
    val (maxY, successList) = Day17.iterateX(Coordinate(0, 0), Box(Coordinate(20, -10), Coordinate(30, -5)))
    val refLine =
      """23,-10  25,-9   27,-5   29,-6   22,-6   21,-7   9,0     27,-7   24,-5
        |25,-7   26,-6   25,-5   6,8     11,-2   20,-5   29,-10  6,3     28,-7
        |8,0     30,-6   29,-8   20,-10  6,7     6,4     6,1     14,-4   21,-6
        |26,-10  7,-1    7,7     8,-1    21,-9   6,2     20,-7   30,-10  14,-3
        |20,-8   13,-2   7,3     28,-8   29,-9   15,-3   22,-5   26,-8   25,-8
        |25,-6   15,-4   9,-2    15,-2   12,-2   28,-9   12,-3   24,-6   23,-7
        |25,-10  7,8     11,-3   26,-7   7,1     23,-9   6,0     22,-10  27,-6
        |8,1     22,-8   13,-4   7,6     28,-6   11,-4   12,-4   26,-9   7,4
        |24,-10  23,-8   30,-8   7,0     9,-1    10,-1   26,-5   22,-9   6,5
        |7,5     23,-6   28,-10  10,-2   11,-1   20,-9   14,-2   29,-7   13,-3
        |23,-5   24,-8   27,-9   30,-7   28,-5   21,-10  7,9     6,6     21,-5
        |27,-10  7,2     30,-9   21,-8   22,-7   24,-9   20,-6   6,9     29,-5
        |8,-2    27,-8   30,-5   24,-7""".stripMargin
    val reflist: Array[Velocity] = refLine.split("\n").map(l=>l.split(" +")).flatten.map(asVelocity(_))
    val newRef = reflist.toVector.sortWith((l, r)=>(l.x<r.x) || (l.x == r.x) && l.y<r.y)
    val newRes = successList.sortWith((l, r)=>(l.x<r.x) || (l.x == r.x) && l.y<r.y).toVector
    println(newRef)
    println(newRes)
    assert(newRef == newRes)
  }

}
