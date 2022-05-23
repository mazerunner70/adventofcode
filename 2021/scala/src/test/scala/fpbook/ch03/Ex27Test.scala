package fpbook.ch03

import fpbook.ch03.Ex27.Branch
import org.scalatest.flatspec.AnyFlatSpec

class Ex27Test extends AnyFlatSpec {

  behavior of "Ex27Test"

  it should "size" in {
    val tree = Ex27.Branch(Ex27.Branch(Ex27.Leaf(2), Ex27.Leaf(5)), Ex27.Leaf(8))
    assert(Ex27.size(tree) == 5)
  }

  it should "product" in {
    val tree = Ex27.Branch(Ex27.Branch(Ex27.Leaf(2), Ex27.Leaf(5)), Ex27.Leaf(8))
    assert(Ex27.product(tree) == 80)
  }

}
