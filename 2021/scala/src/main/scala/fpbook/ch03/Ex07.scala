package fpbook.ch03

import scala.annotation.tailrec

object Ex07 {
  @tailrec
  def foldLeft[A,B](as: List[A], x: B)(f: (B, A) => B): B = as match {
    case Nil => x
    case h :: t => foldLeft(t, f(x, h))(f)
  }

}
