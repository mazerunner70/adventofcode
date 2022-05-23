package fpbook.ch02

import scala.annotation.tailrec

object Ex01 {
  def fibonacci(i: Int): Int = {
    @tailrec
    def inner(i: Int, t: Int, tm1: Int): Int = {
      if (i <2) t
      else
        inner(i-1, t+tm1, t)
    }
    if (i <2) i else inner(i, 1, 0)
  }


}
