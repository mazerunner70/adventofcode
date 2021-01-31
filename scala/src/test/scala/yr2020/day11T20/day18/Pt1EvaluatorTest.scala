package yr2020.day11T20.day18

import org.scalatest.flatspec.AnyFlatSpec

class Pt1EvaluatorTest extends AnyFlatSpec {

  behavior of "Pt1EvaluatorTest"

  it should "calculate" in {
    assert(Pt1Evaluator.calculate("1 + (2 * 3) + (4 * (5 + 6))") == 51)
    assert( Pt1Evaluator.calculate("2 * 3 + (4 * 5)") == 26)
    assert( Pt1Evaluator.calculate("5 + (8 * 3 + 9 + 3 * 4 * 3)") == 437)
    assert( Pt1Evaluator.calculate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))") == 12240)
    assert( Pt1Evaluator.calculate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") == 13632)
  }

}
