package fpbook.ch04

object Ex04 {

  // How this works:
  // the h.flatmap acts as a bar to further processing, if h is ever None
  // if h is never null, processing moves to the .map, which will accumulate values (_) as the flatmap unwinds

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case h :: t => h.flatMap(hh => sequence(t).map(hh :: _))
  }
}
