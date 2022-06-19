package yr2021.days

object Day18 {
  // based on https://stackoverflow.com/questions/23819616/tree-represented-as-a-tuple-in-scala
  type TreeType = Int
  sealed trait Tree(int )
  case class Branch(trees: Seq[Tree]) extends Tree
  case class Leaf(value: TreeType) extends Tree

  def count(t: Tree): Int = t match {
    case Branch(children) => 1 + children.map(count).sum
    case Leaf(v) => 1
  }

  def add (left: Tree, right: Tree): Tree =
    Branch(Seq(left, right))

  def explode(t: Tree): Tree = {
    
  }
}
