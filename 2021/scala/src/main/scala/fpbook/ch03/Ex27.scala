package fpbook.ch03

object Ex27 {

  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  def fold[A, B](tree: Tree[A])(f: (A) => B, g: (B, B)=>B): B = tree match {
    case Leaf(value) => f(value)
    case Branch(left, right) => g(fold(left)(f, g), fold(right)(f, g))
  }

  def size[A](tree: Tree[A]): Int = {
    def inner(tree: Tree[A]): Int = tree match {
      case Branch(left, right) => inner(left) + inner(right) +1
      case Leaf(_) => 1
    }
    inner(tree)
  }

  def sizeViaFold[A](tree: Tree[A]): Int = {
    fold[A, Int](tree)(a=>1, 1+_+_)
  }



  def product(tree: Tree[Int]): Int = {
    def inner(tree: Tree[Int]): Int = tree match {
      case Branch(left, right) => inner(left) * inner(right)
      case Leaf(value) => value
    }
    inner(tree)
  }



}
