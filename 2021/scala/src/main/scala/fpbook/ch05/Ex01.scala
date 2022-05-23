package fpbook.ch05

import fpbook.ch05.Ch05Stream.{cons, empty}

import scala.annotation.tailrec

sealed trait Ch05Stream[+A] {
  def headOption: Option[A] = this match {
    case Ch05Empty => None
    case Ch05Cons(h, _) => Some(h())
  }
  def toList: List[A] = {
    def inner(cons: Ch05Stream[A], acc: List[A]): List[A] = cons match {
      case Ch05Cons(h, t) =>  inner(t(), h() :: acc)
      case _ => acc
    }
    inner(this, List.empty).reverse
  }
  def take(n: Int): List[A] = {
    @tailrec
    def inner(streamPos: Ch05Stream[A], countDown: Int, acc: List[A]): List[A] = (streamPos, countDown) match {
      case (_, 0) => acc.reverse
      case (Ch05Cons(h, t), _) => inner(t(), countDown-1, h() :: acc)
    }
    inner(this, n, List())
  }
  def takeWhile(p: A=>Boolean): List[A] = {
    @tailrec
    def inner(streamPos: Ch05Stream[A], acc: List[A]): List[A] = streamPos match {
      case Ch05Cons(h, t) if (p(h())) => h() :: acc
      case Ch05Cons(h, t) =>  inner(t(), h() :: acc)
    }
    inner(this, List())
  }

  @tailrec
  final def drop(n: Int): Ch05Stream[A] = (this, n) match {
    case (c, 0) => c
    case (Ch05Cons(_, t), _) => t().drop(n-1)
  }

  final def forAll(p: A=> Boolean): Boolean = {
    def inner(streamPos: Ch05Stream[A] ): Boolean = streamPos match {
      case Ch05Empty => true
      case Ch05Cons(h, t) if p(h()) => inner(t())
      case _ => false
    }
    inner(this)
  }

  def foldRight[B](z: =>B)(f: (A, =>B) => B): B =
    this match {
      case Ch05Cons(h, t) => f(h(), t().foldRight(z)(f))
      case _ => z
    }

  def foldedTakeWhile(p: A=>Boolean): Ch05Stream[A] = {
    foldRight(empty[A])((h, t)=> if (p(h)) cons(h, t) else empty)
  }

  def foldedHeadOption(): Option[A] = {
    foldRight(None: Option[A])((h, _)=> Some(h) )
  }

  def foldedMap[B](f:A=>B): Ch05Stream[B] =
    foldRight(empty[B])((h, t)=>cons(f(h), t))

  def foldedFilter(p:A=>Boolean): Ch05Stream[A] =
    foldRight(empty[A])((h, t)=>if (p(h)) cons(h, t) else t)

  def foldedAppend[B>:A](s: => Ch05Stream[B]): Ch05Stream[B] =
    foldRight(s)((h, t)=>cons(h, t))

  def foldedFlatMap[B](f: A => Ch05Stream[B]): Ch05Stream[B] =
    foldRight(empty[B])((h,t) => f(h) foldedAppend t)

  def unfold[A, S] (z: S)(f: S => Option[(A, S)]): Ch05Stream[A] = f(z) match {
    case Some((h,s)) => cons(h, unfold(s)(f))
    case None => empty
  }
  def mapViaUnfold[B](f: A => B): Ch05Stream[B] =
    unfold(this) {
      case Ch05Cons(h,t) => Some((f(h()), t()))
      case _ => None
    }

}
case object Ch05Empty extends Ch05Stream[Nothing]
case class Ch05Cons[+A](h: () => A, t: () => Ch05Stream[A]) extends Ch05Stream[A]

object Ch05Stream {
  def cons[A](hd: => A, tl: =>Ch05Stream[A]): Ch05Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Ch05Cons(() => head, () => tail)
  }
  def empty[A]: Ch05Stream[A] = Ch05Empty

  def apply[A](as: A*): Ch05Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*)) // example of type ascription, as Scala can't see I need a vararg ??

  val ones: Ch05Stream[Int] = Ch05Stream.cons(1, ones)
  def constant[A](a: A): Ch05Stream[A] = {
    cons(a, constant(a))
  }
  def from (n: Int): Ch05Stream[Int] =
    cons(n, from(n+1))

  def fibs: Ch05Stream[Int] = {
    def fib(x: Int, y: Int): Ch05Stream[Int] =
      cons(x, fib(y, x+y))
    fib(0,1)
  }
  def unfold[A, S] (z: S)(f: S => Option[(A, S)]): Ch05Stream[A] = f(z) match {
    case Some((h,s)) => cons(h, unfold(s)(f))
    case None => empty
  }
  def unfoldedFibs: Ch05Stream[Int] = {
    unfold((0,1)){case (x, y) => Some((x, (y, x + y)))}
  }
  def unfoldedFrom(i: Int): Ch05Stream[Int] = {
    unfold(i)(i=>Some(i, i+1))
  }
  def constantViaUnfold[A](a: A) =
    unfold(a)(_ => Some((a,a)))

  // could also of course be implemented as constant(1)
  val onesViaUnfold = unfold(1)(_ => Some((1,1)))

}

object Ex01 {

}
