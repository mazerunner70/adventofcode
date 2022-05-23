package fpbook.ch04

object Ex02 {

  def mean(xs: Seq[Double]): Option[Double] = xs.size match {
    case 0 => None
    case s => Some(xs.sum / s)
  }

  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs).flatMap(m=> mean(xs.map(x=>math.pow(x-m, 2))))


}
