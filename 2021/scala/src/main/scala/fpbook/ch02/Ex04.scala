package fpbook.ch02

object Ex04 {

  def uncurry[A, B, C](f: A=>B=>C): (A, B)=>C = {
    (a:A, b:B)=> f(a)(b)
  }


}
