package yr2021.common

import scala.annotation.tailrec

sealed trait FpStream[+A] {
  def take(n: Int): List[A] = {
    @tailrec
    def inner(streamPos: FpStream[A], countDown: Int, acc: List[A]): List[A] = (streamPos, countDown) match {
      case (_, 0) => acc.reverse
      case (FpCons(h, t), _) => inner(t(), countDown-1, h() :: acc)
    }
    inner(this, n, List())
  }
}
case object Empty extends FpStream[Nothing]
case class FpCons[+A](h: ()=>A, t: () => FpStream[A]) extends FpStream[A]

object FpStream {
  def cons[A](hd: => A, tl: => FpStream[A]): FpStream[A] = {
    lazy val head = hd
    lazy val tail = tl
    FpCons(()=> head, () => tail)
  }
  def empty[A]: FpStream[A] = Empty
  def apply[A](as: A*): FpStream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  def unfold[A, S] (z: S)(f: S => Option[(A, S)]): FpStream[A] = f(z) match {
    case Some((h,s)) => cons(h, unfold(s)(f))
    case None => empty
  }

}




