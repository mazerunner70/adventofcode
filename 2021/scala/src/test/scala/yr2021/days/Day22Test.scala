package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.{Cell, CoordSet, CoordinateND, InputData}
import yr2021.days.Day22.{Cuboid, Step}

class Day22Test extends AnyFlatSpec {

  it should "parselines" in {
    assert(Day22.parse("on x=10..12,y=10..12,z=10..12") == Some(Step(true, Cuboid(10 to 12, 10 to 12, 10 to 12))))
    assert(Day22.parse("on x=11..13,y=11..13,z=11..13") == Some(Step(true, Cuboid(11 to 13, 11 to 13, 11 to 13))))
    assert(Day22.parse("off x=9..11,y=9..11,z=9..11") == Some(Step(false, Cuboid(9 to 11, 9 to 11, 9 to 11))))
    assert(Day22.parse("on x=10..10,y=10..10,z=10..10") == Some(Step(true, Cuboid(10 to 10, 10 to 10, 10 to 10))))
  }

  it should "cuboid intersect" in {
    assert(Cuboid(1 to 1, 1 to 1, 1 to 1).intersect(Cuboid(1 to 1, 1 to 1, 1 to 1)) ==
      Some(Cuboid(1 to 1, 1 to 1, 1 to 1)))
    assert(Cuboid(1 to 2, 1 to 2, 0 to 3).intersect(Cuboid(1 to 1, 1 to 1, 1 to 1)) ==
      Some(Cuboid(1 to 1, 1 to 1, 1 to 1)))
    assert(Cuboid(1 to 2, 1 to 1, 0 to 1).intersect(Cuboid(0 to 1, 1 to 5, -1 to 1)) ==
      Some(Cuboid(1 to 1, 1 to 1, 0 to 1)))
    assert(Cuboid(2 to 2, 1 to 1, 0 to 1).intersect(Cuboid(0 to 1, 1 to 5, -1 to 1)) ==
      None)

  }

  def getResult(count: Int) = {
    val input = """on x=10..12,y=10..12,z=10..12
                  |on x=11..13,y=11..13,z=11..13
                  |off x=9..11,y=9..11,z=9..11
                  |on x=10..10,y=10..10,z=10..10""".stripMargin
    val steps = input.split('\n').map(Day22.parse(_)).flatten.toSeq.slice(0,count)
    val coordSet = CoordSet[Boolean](Set())
    Day22.filterTrue(Day22.addSteps(coordSet, steps))
  }

  it should "test1" in {
    val target = """10,10,10
                   |10,10,11
                   |10,10,12
                   |10,11,10
                   |10,11,11
                   |10,11,12
                   |10,12,10
                   |10,12,11
                   |10,12,12
                   |11,10,10
                   |11,10,11
                   |11,10,12
                   |11,11,10
                   |11,11,11
                   |11,11,12
                   |11,12,10
                   |11,12,11
                   |11,12,12
                   |12,10,10
                   |12,10,11
                   |12,10,12
                   |12,11,10
                   |12,11,11
                   |12,11,12
                   |12,12,10
                   |12,12,11
                   |12,12,12""".stripMargin.split('\n').map(l=> CoordinateND(l.split(',').map(_.toInt).toVector)).map(Cell(_, true)).toSet

    val f = getResult(1)
    assert (f.cs== target)
  }

  it should "test2" in {
    val target = """10,10,10
                   |10,10,11
                   |10,10,12
                   |10,11,10
                   |10,11,11
                   |10,11,12
                   |10,12,10
                   |10,12,11
                   |10,12,12
                   |11,10,10
                   |11,10,11
                   |11,10,12
                   |11,11,10
                   |11,11,11
                   |11,11,12
                   |11,12,10
                   |11,12,11
                   |11,12,12
                   |12,10,10
                   |12,10,11
                   |12,10,12
                   |12,11,10
                   |12,11,11
                   |12,11,12
                   |12,12,10
                   |12,12,11
                   |12,12,12
                   |11,11,13
                   |11,12,13
                   |11,13,11
                   |11,13,12
                   |11,13,13
                   |12,11,13
                   |12,12,13
                   |12,13,11
                   |12,13,12
                   |12,13,13
                   |13,11,11
                   |13,11,12
                   |13,11,13
                   |13,12,11
                   |13,12,12
                   |13,12,13
                   |13,13,11
                   |13,13,12
                   |13,13,13""".stripMargin.split('\n').map(l=> CoordinateND(l.split(',').map(_.toInt).toVector)).map(Cell(_, true)).toSet

    val f = getResult(2)
    assert (f.cs== target)
  }
  it should "test3" in {
    val target = """10,10,12
                   |10,11,12
                   |10,12,10
                   |10,12,11
                   |10,12,12
                   |11,10,12
                   |11,11,12
                   |11,12,10
                   |11,12,11
                   |11,12,12
                   |12,10,10
                   |12,10,11
                   |12,10,12
                   |12,11,10
                   |12,11,11
                   |12,11,12
                   |12,12,10
                   |12,12,11
                   |12,12,12
                   |11,11,13
                   |11,12,13
                   |11,13,11
                   |11,13,12
                   |11,13,13
                   |12,11,13
                   |12,12,13
                   |12,13,11
                   |12,13,12
                   |12,13,13
                   |13,11,11
                   |13,11,12
                   |13,11,13
                   |13,12,11
                   |13,12,12
                   |13,12,13
                   |13,13,11
                   |13,13,12
                   |13,13,13""".stripMargin.split('\n').map(l=> CoordinateND(l.split(',').map(_.toInt).toVector)).map(Cell(_, true)).toSet

    val f = getResult(3)
    assert (f.cs== target)
  }

  it should "test4" in {
    val f = getResult(4)
    assert(f.cs.size == 39)
  }

  it should "test5" in {
    val steps = InputData.fromFile("days/day22/test.txt").asLineList.map(Day22.parse(_)).flatten.toSeq
    val coordSet = CoordSet[Boolean](Set())
    val results = Day22.filterTrue(Day22.addSteps(coordSet, steps))
    assert(results.cs.size == 590784)
  }

  it should "part1" in {
    val steps = InputData.fromFile("days/day22/input.txt").asLineList.map(Day22.parse(_)).flatten.toSeq
    val coordSet = CoordSet[Boolean](Set())
    val results = Day22.filterTrue(Day22.addSteps(coordSet, steps))
    assert(results.cs.size == 601104)
  }

  it should "CM: steps" in {
    val input = """on x=10..12,y=10..12,z=10..12
                  |on x=11..13,y=11..13,z=11..13
                  |off x=9..11,y=9..11,z=9..11
                  |on x=10..10,y=10..10,z=10..10""".stripMargin
    val steps = input.split('\n').map(Day22.parse(_)).flatten.toList
    val res = Seq((2, 46), (3, 38), (4, 39))
    for (x <- res.slice(2, 3)) {
      val cs = Day22.removeOverlaps(steps.slice(0, x._1).reverse, Nil)
      assert(Day22.size(cs) == x._2, " for size: "+x._1)
    }

  }

  it should "extract2" in {
    val cs: List[Cuboid] = List(
      Cuboid(10 to 10, 10 to 10, 10 to  10),
      Cuboid(9 to 11, 9 to 11, 9 to  11)
    )
    val res = cs(0).extract(cs(1))
    val inv = cs(0).intersect(cs(1)).get.volume
    assert(res.map(_.volume).sum == cs(0).volume-inv)
  }
  it should "extract3" in {
    val cs: List[Cuboid] = List(
      Cuboid(9 to 11, 9 to 11, 9 to  11),
      Cuboid(10 to 10, 10 to 10, 10 to  10),
    )
    val res = cs(0).extract(cs(1))
    val inv = cs(0).intersect(cs(1)).get.volume
    assert(res.toList.map(_.volume).sum == cs(0).volume-inv)
  }
  it should "extract1" in {
    val cs: List[Cuboid] = List(
      Cuboid(10 to 12, 10 to 12, 10 to  12),
      Cuboid(11 to 13, 11 to 13, 11 to  13),
    )
    val res = cs(1).extract(cs(0))
    assert(res.map(_.volume).sum == cs(0).volume-cs(0).intersect(cs(1)).get.volume)
  }
  it should "extract4" in {
    val cs = """on x=-41..5,y=-41..6,z=-36..8
               |off x=-43..-33,y=-45..-28,z=7..25""".stripMargin.split('\n').map(Day22.parse(_)).flatten.toSeq.map(_.box)
    val res = cs(1).extract(cs(0))
    val e = res.map(_.volume)
    assert(e.sum == cs(0).volume-cs(0).intersect(cs(1)).get.volume)
  }
  it should "extract5" in {
    val cs = """on x=-41..5,y=-41..6,z=-36..8
               |off x=-43..-33,y=-45..-28,z=7..25""".stripMargin.split('\n').map(Day22.parse(_)).flatten.toSeq.map(_.box)
    val res = cs(0).extract(cs(1))
    val e = res.map(_.volume)
    assert(e.sum == 101268)
  }

  it should "test6" in {
    val steps = InputData.fromFile("days/day22/test1.txt").asLineList.slice(0,60)
    println("lines: \n"+steps.mkString("\n"))
    val cuboids = Day22.resolve(steps.map(Day22.parse(_)).flatten)
    assert(Day22.size(cuboids) == 2758514936282235L)
  }
  it should "part2" in {
    val steps = InputData.fromFile("days/day22/input.txt").asLineList
    println("lines: \n"+steps.mkString("\n"))
    val cuboids = Day22.resolve(steps.map(Day22.parse(_)).flatten)
    assert(Day22.size(cuboids) == 2758514936282235L)
  }


}
