package yr2021.days.fppractice

import org.scalatest.flatspec.AnyFlatSpec

class Day16CatsPracticeTest extends AnyFlatSpec {

  behavior of "Day16CatsPracticeTest"

  it should "AsString" in {
    val day16CatsPractice = new Day16CatsPractice()
    assert(day16CatsPractice.intAsString == "123")
    assert(day16CatsPractice.stringAsString == "abc")
  }

  it should "Shown" in {
    val day16CatsPractice = new Day16CatsPractice()
    assert(day16CatsPractice.shownInt == "123")
    assert(day16CatsPractice.shownString == "abc")
  }

  it should "datestring" in {
    val day16CatsPractice = new Day16CatsPractice()
    assert(day16CatsPractice.dateString.split("ms")(1) == " since the epoch.")
  }

  it should "intfieldstring" in {
    val day16CatsPractice = new Day16CatsPractice()
    assert(day16CatsPractice.shownIntField == "value is 67")
  }

  it should "date compare" in {
    val day16CatsPractice = new Day16CatsPractice()
    assert(day16CatsPractice.dateCompare1 == true)
    assert(day16CatsPractice.dateCompare2 == false)
  }

  it should "intField compare" in {
    val day16CatsPractice = new Day16CatsPractice()
    assert(day16CatsPractice.intFieldCompare1 == true)
    assert(day16CatsPractice.intFieldCompare2 == false)
  }

}
