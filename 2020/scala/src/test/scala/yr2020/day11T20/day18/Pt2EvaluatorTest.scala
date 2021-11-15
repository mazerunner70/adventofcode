package yr2020.day11T20.day18

import org.scalatest.flatspec.AnyFlatSpec

class Pt2EvaluatorTest extends AnyFlatSpec {

  behavior of "Pt2EvaluatorTest"

  it should "calculate" in {
    assert(Pt2Evaluator.calculate("1 + 2 * 3 + 4 * 5 + 6") == 231)
    assert(Pt2Evaluator.calculate("1 + (2 * 3) + (4 * (5 + 6))") == 51)
    assert(Pt2Evaluator.calculate("2 * 3 + (4 * 5)") == 46)
    assert(Pt2Evaluator.calculate("5 + (8 * 3 + 9 + 3 * 4 * 3)") == 1445)
    assert(Pt2Evaluator.calculate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))") == 669060)
    assert(Pt2Evaluator.calculate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") == 23340)

  }

}
