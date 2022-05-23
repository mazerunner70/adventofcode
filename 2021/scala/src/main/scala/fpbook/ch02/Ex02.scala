package fpbook.ch02

import scala.annotation.tailrec

object Ex02 {
  @tailrec
  def isSorted[A](value: Seq[A], ordered: (A, A)=>Boolean): Boolean =
    if (value.size < 2) true else {
      if (ordered(value.head, value.tail.head))
        isSorted(value.tail, ordered)
      else
        false
    }


}
