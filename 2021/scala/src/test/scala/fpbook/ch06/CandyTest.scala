package fpbook.ch06
import Candy._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class CandyTest extends AnyFlatSpec {

  it should "candy test" in {
    val inputCoin = List(Coin)
    val inputTurn = List(Turn)

    // Inserting a coin into a locked machine will cause it to unlock if there’s any candy left.
    val machine1 = Machine(true, 1, 0)
    simulateMachine(inputCoin).run(machine1)._2.locked shouldBe false

    // Turning the knob on an unlocked machine will cause it to dispense candy and become locked.
    val machine2 = Machine(false, 1, 1)
    val m2Result = simulateMachine(inputTurn).run(machine2)
    m2Result._2.locked shouldBe true
    m2Result._2.candies shouldBe 0

    // Turning the knob on a locked machine or inserting a coin into an unlocked machine does nothing.
    simulateMachine(inputTurn).run(machine1)._2.locked shouldBe machine1.locked
    simulateMachine(inputCoin).run(machine2)._2.locked shouldBe machine2.locked

    // A machine that’s out of candy ignores all inputs.
    val machine3 = Machine(true, 0, 1)
    simulateMachine(inputTurn).run(machine3)._2.locked shouldBe machine3.locked
    simulateMachine(inputCoin).run(machine3)._2.locked shouldBe machine3.locked
  }

}
