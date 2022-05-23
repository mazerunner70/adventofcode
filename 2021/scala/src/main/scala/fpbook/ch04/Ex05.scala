package fpbook.ch04

object Ex05 {

  //  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B)=> C): Option[C] = {
  //    a.flatMap(xa=>b.flatMap(xb=>Some(f(xa, xb))))
  //  }
  //  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
  //    case Nil => Some(Nil)
  //    case h :: t => h.flatMap(hh => sequence(t).map(hh :: _))
  //  }

  def traverse[A, B](a: List[A])(f:A => Option[B]): Option[List[B]] = a match {
    case Nil =>  Some(Nil)
    case h :: t => f(h).flatMap (fh => traverse(t)(f).map(fh :: _))
  }

}
