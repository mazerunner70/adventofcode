package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.{Grid, InputData}
import yr2021.days.Day20.Iea

class Day20Test extends AnyFlatSpec {

//  it should "test transformer" in {
//    val blocks = InputData.fromFile("days/day20/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
//    val iea = Iea(blocks(0).head.map(".#".indexOf(_)).toVector)
//    def toInt(l: String)= l.map(".#".indexOf(_)).toVector
//    val grid = Grid.asGrid(blocks(1), toInt )
//    assert(Day20.tra)
//  }

  it should "loadgrid" in {
    val blocks = InputData.fromFile("days/day20/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
    val iea = Iea(blocks(0).head.map(".#".indexOf(_)).toVector)
    def toInt(l: String)= l.map(".#".indexOf(_)).toVector
    val grid = Grid.asGrid(blocks(1), toInt )
    val c1 = Day20.transform(grid, iea, 0)
    assert(c1 == Grid(Vector(Vector(0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 1, 1, 0, 1, 1, 0, 0), Vector(0, 1, 0, 0, 1, 0, 1, 0, 0), Vector(0, 1, 1, 0, 1, 0, 0, 1, 0), Vector(0, 1, 1, 1, 1, 0, 0, 1, 0), Vector(0, 0, 1, 0, 0, 1, 1, 0, 0), Vector(0, 0, 0, 1, 1, 0, 0, 1, 0), Vector(0, 0, 0, 0, 1, 0, 1, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0))))
    val c2 = Day20.transform(c1, iea, 0)
    assert (c2 == Grid(Vector(Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0), Vector(0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0), Vector(0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0), Vector(0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0), Vector(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0), Vector(0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0), Vector(0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0), Vector(0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))))
    assert(Day20.countOn(c2) == 35)
    assert(Day20.transformSequence(grid, iea, 2) == Grid(Vector(Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0), Vector(0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0), Vector(0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0), Vector(0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0), Vector(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0), Vector(0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0), Vector(0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0), Vector(0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))))
    assert(Day20.countOn(Day20.transformSequence(grid, iea, 50)) == 3351)
  }

  it should "part1" in {
    val blocks = InputData.fromFile("days/day20/input.txt").multiLineRecordParse(InputData.emptyLineSeparater)
    val iea = Iea(blocks(0).head.map(".#".indexOf(_)).toVector)
    def toInt(l: String)= l.map(".#".indexOf(_)).toVector
    val grid = Grid.asGrid(blocks(1), toInt )

    assert(Day20.countOn(Day20.transformSequence(grid, iea, 2)) == 5231)
    assert(Day20.countOn(Day20.transformSequence(grid, iea, 50)) == 5231)
  }

}
