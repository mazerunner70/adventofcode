package fpbook2.ch02

object Ch02 {

  def fib(n: Int): Int = {
    def f(n: Int): Int = n match {
      case 0 => 0
      case 1 => 1
      case _ => f(n-1) + f(n-2)
    }
    f(n)
  }

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean =
    (1 until as.size).foldLeft(true)((acc, e)=> acc && ordered(as(e-1), as(e)))

  def curry[A, B, C](f: (A, B)=>C): A=> (B=>C) = {
    (a: A) => (b: B) => f(a, b)
  }

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a, b) => f(a)(b)
  }

  def compose[A, B, C] (f: B=> C, g: A=> B): A => C = {
    a=>f(g(a))
  }

}
