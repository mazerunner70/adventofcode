package yr2021.fpdays.day01

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.fpcommon.PuzzleInput.{loadFromFile, loadLinesFromFile}
import cats.effect.unsafe.implicits.global

class Day01Test extends AnyFlatSpec {

  behavior of "Day01Test"

  it should "pt1-test" in {
    val sourceName = "days/day01/test.txt"
    val day01 = new Day01
    val text = for {
      resourceData <- loadLinesFromFile(sourceName)
    } yield day01.pt1(resourceData.lines)
    assert(text.unsafeRunSync == 7)
  }

  it should "pt1" in {
    val sourceName = "days/day01/input.txt"
    val day01 = new Day01
    val text = for {
      resourceData <- loadLinesFromFile(sourceName)
    } yield day01.pt1(resourceData.lines)
    assert(text.unsafeRunSync == 1713)
  }

  it should "pt2-test" in {
    val sourceName = "days/day01/test.txt"
    val day01 = new Day01
    val text = for {
      resourceData <- loadLinesFromFile(sourceName)
    } yield day01.pt2(resourceData.lines)
    assert(text.unsafeRunSync == 5)
  }

  it should "pt2" in {
    val sourceName = "days/day01/input.txt"
    val day01 = new Day01
    val text = for {
      resourceData <- loadLinesFromFile(sourceName)
    } yield day01.pt2(resourceData.lines)
    assert(text.unsafeRunSync == 1734)
  }

}
