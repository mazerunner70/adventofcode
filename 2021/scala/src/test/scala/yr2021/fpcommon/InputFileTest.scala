package yr2021.fpcommon

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.fpcommon.PuzzleInput._
import cats.effect.unsafe.implicits.global

class InputFileTest extends AnyFlatSpec {

  behavior of "InputFileTest"

  it should "stringFromFile" in {
    val sourceName = "fpcommon/test1.txt"
    val text = for {
      resourceData <- loadFromFile(sourceName)
    } yield resourceData
    assert(text.unsafeRunSync == "test1\ntest2\n\ntest3")
  }

  it should "asLines" in {
    val sourceName = "fpcommon/test1.txt"
    val text = loadLinesFromFile(sourceName)
    assert(text.unsafeRunSync == PuzzleInput(Seq("test1", "test2","" , "test3")))
  }


}
