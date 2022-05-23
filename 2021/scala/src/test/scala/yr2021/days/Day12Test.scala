package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.days

class Day12Test extends AnyFlatSpec {

  behavior of "Day12Test"

  it should "onwardOptions" in {
    val day12 = new Day12
    val lines = List("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end").map(day12.parseLine(_))
    assert(day12.onwardConnections(day12.getConnections(lines)) == Map(
      Cavern("c") -> List(Connection(Cavern("c"), Cavern("A"))),
      Cavern("b") -> List(Connection(Cavern("b"), Cavern("d")), Connection(Cavern("b"), Cavern("end")), Connection(Cavern("b"), Cavern("start")), Connection(Cavern("b"), Cavern("A"))),
      Cavern("A") -> List(Connection(Cavern("A"), Cavern("c")), Connection(Cavern("A"), Cavern("b")), Connection(Cavern("A"), Cavern("end")), Connection(Cavern("A"), Cavern("start"))),
      Cavern("d") -> List(Connection(Cavern("d"), Cavern("b"))), Cavern("start") -> List(Connection(Cavern("start"), Cavern("A")), Connection(Cavern("start"), Cavern("b"))),
      Cavern("end") -> List(Connection(Cavern("end"), Cavern("A")), Connection(Cavern("end"), Cavern("b")))))
  }
  it should "createNodeMap" in {
    val day12 = new Day12
    val lines = List("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end").map(day12.parseLine(_))
    assert(day12.createCavernNameLookup(lines) == Map("A" -> Cavern("A"), "b" -> Cavern("b"), "end" -> Cavern("end"), "c" -> Cavern("c"), "start" -> Cavern("start"), "d" -> Cavern("d")))
  }

  it should "parseLine" in {
    val day12 = new Day12
    assert(day12.parseLine("start-A") == Seq("start", "A"))
  }

  it should "calculateTraversals" in {
    val day12 = new Day12
    val lines = List("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end").map(day12.parseLine(_))
    val onwardOptions = day12.onwardConnections(day12.getConnections(lines))
    assert(day12.calculateTraversals(onwardOptions, day12.pt1OnwardCavernFilter).size == 10)
  }

  it should "getPaths" in {
    val day12 = new Day12
    val lines = List("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end").map(day12.parseLine(_))
    assert(day12.getConnections(lines)  == List(Connection(Cavern("start"),Cavern("A")), Connection(Cavern("start"),Cavern("b")), Connection(Cavern("A"),Cavern("c")), Connection(Cavern("A"),Cavern("b")), Connection(Cavern("b"),Cavern("d")), Connection(Cavern("A"),Cavern("end")), Connection(Cavern("b"),Cavern("end")), Connection(Cavern("A"),Cavern("start")), Connection(Cavern("b"),Cavern("start")), Connection(Cavern("c"),Cavern("A")), Connection(Cavern("b"),Cavern("A")), Connection(Cavern("d"),Cavern("b")), Connection(Cavern("end"),Cavern("A")), Connection(Cavern("end"),Cavern("b"))))
  }

  def prettyPrint(routes: Seq[Seq[Cavern]]):Seq[String] = {
    routes.map(r=>r.map(n=>n.name).mkString("-"))
  }

  it should "pt1-test1" in {
    val day12 = new Day12
    val testLines = List("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end")
    val output = day12.pt1(testLines)
    assert(prettyPrint(output) == List("start-b-A-end", "start-b-A-c-A-end", "start-b-end", "start-A-end", "start-A-b-A-end", "start-A-b-A-c-A-end", "start-A-b-end", "start-A-c-A-end", "start-A-c-A-b-A-end", "start-A-c-A-b-end"))
  }
  it should "pt1-test2" in {
    val day12 = new Day12
    val testLines = loadList("days/day12/test.txt")
    val output = day12.pt1(testLines)
    assert(prettyPrint(output).size == 19)
  }
  it should "pt1-test3" in {
    val day12 = new Day12
    val testLines = loadList("days/day12/test2.txt")
    val output = day12.pt1(testLines)
    assert(prettyPrint(output).size == 226)
  }
  it should "pt1" in {
    val day12 = new Day12
    val lines = loadList("days/day12/input.txt")
    val output = day12.pt1(lines)
    assert(prettyPrint(output).size == 5756)
  }

  it should "pt2-test1" in {
    val day12 = new Day12
    val testLines = List("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end")
    val output = day12.pt2(testLines)
    assert(prettyPrint(output).size == 36)
//    assert(prettyPrint(output).sorted == List("start-b-A-end", "start-b-A-c-A-end", "start-b-end", "start-A-end", "start-A-b-A-end", "start-A-b-A-c-A-end", "start-A-b-end", "start-A-c-A-end", "start-A-c-A-b-A-end", "start-A-c-A-b-end"))
  }
  it should "pt2-test1a" in {
    val day12 = new Day12
    val testLines = List("start-A",  "A-end")
    val output = day12.pt2(testLines)
    assert(prettyPrint(output).size == 1)
    assert(prettyPrint(output).sorted == List("start-A-end"))
  }
  it should "pt2-test2" in {
    val day12 = new Day12
    val testLines = loadList("days/day12/test2.txt")
    val output = day12.pt2(testLines)
    assert(prettyPrint(output).size == 3509)
//    assert(prettyPrint(output) == List("start-b-A-end", "start-b-A-c-A-end", "start-b-end", "start-A-end", "start-A-b-A-end", "start-A-b-A-c-A-end", "start-A-b-end", "start-A-c-A-end", "start-A-c-A-b-A-end", "start-A-c-A-b-end"))
  }
  it should "pt2" in {
    val day12 = new Day12
    val lines = loadList("days/day12/input.txt")
    val output = day12.pt2(lines)
    assert(prettyPrint(output).size == 5755)
  }

  it should "pt2OnwardCavernFilter" in {
    val day12 = new Day12
    val visibleCaverns = List(Cavern("A"), Cavern("b"))
    val traversed = List(Cavern("start"))
    assert(day12.pt2OnwardCavernFilter(traversed, visibleCaverns) == List(Cavern("A"), Cavern("b")))
  }
  it should "pt2OnwardCavernFilter2" in {
    val day12 = new Day12
    val visibleCaverns = List(Cavern("A"), Cavern("b"))
    val traversed = List(Cavern("start"), Cavern("b"), Cavern("c"))
    assert(day12.pt2OnwardCavernFilter(traversed, visibleCaverns) == List(Cavern("A"), Cavern("b")))
  }
  it should "pt2OnwardCavernFilter3" in {
    val day12 = new Day12
    val visibleCaverns = List(Cavern("A"), Cavern("b"))
    val traversed = List(Cavern("start"), Cavern("b"), Cavern("c"), Cavern("b"))
    assert(day12.pt2OnwardCavernFilter(traversed, visibleCaverns) == List(Cavern("A")))
  }
//  it should "pt2MaxVisits" in {
//    val day12 = new Day12
//    assert(day12.pt2MaxVisits(Cavern("start")) == 1)
//    assert(day12.pt2MaxVisits(Cavern("end")) == 1)
//    assert(day12.pt2MaxVisits(Cavern("A")) == 2)
//  }
  it should "countSecondVisits" in {
    val day12 = new Day12
    assert(day12.pt2CountSecondVisits((List(Cavern("start"), Cavern("a")))) == 0)
    assert(day12.pt2CountSecondVisits(List(Cavern("start"), Cavern("a"), Cavern("a"))) == 1)
    assert(day12.pt2CountSecondVisits(List(Cavern("start"), Cavern("a"), Cavern("b"), Cavern("a"))) == 1)
  }

}
