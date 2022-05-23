package fpbook.ch04

object Ex03 {

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B)=> C): Option[C] = {
    a.flatMap(xa=>b.flatMap(xb=>Some(f(xa, xb))))
  }

}
