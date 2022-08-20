package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Coordinate2D

class Day13Test extends AnyFlatSpec {

  behavior of "Day13Test"

  it should "pt1-test" in {
    val day13 = new Day13
    val lines = loadList("days/day13/test.txt")
    assert(day13.pt1(lines) == 17)
  }
  it should "pt1" in {
    val day13 = new Day13
    val lines = loadList("days/day13/input.txt")
    assert(day13.pt1(lines) == 850)
  }

  def prettyPrint(coordinates:List[Coordinate2D]) = {
    val rows = coordinates.groupBy(_.y).map(en=>(en._1, en._2.map(_.x).sorted)).toList.sortBy(r=>r._1)
    rows.map(r=>0::r._2 zip r._2).foreach { rl =>
      val row = rl.map(tup => tup._2 - tup._1-1).map(g=>List.fill(g)(" ") ++ "#").flatten.mkString
      println(row)
    }

  }
  it should "pt2-test" in {
    val day13 = new Day13
    val lines = loadList("days/day13/test.txt")
    prettyPrint(day13.pt2(lines))
  }
  it should "pt2" in {
    val day13 = new Day13
    val lines = loadList("days/day13/input.txt")
    prettyPrint(day13.pt2(lines))
  }





}
