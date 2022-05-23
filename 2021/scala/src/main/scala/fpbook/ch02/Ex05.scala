package fpbook.ch02

object Ex05 {

  def compose[A, B, C](f: B=>C, g: A=>B): A=>C = {
    f compose g
  }

}
