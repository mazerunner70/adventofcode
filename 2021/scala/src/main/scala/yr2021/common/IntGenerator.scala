package yr2021.common

import com.sun.javafx.image.IntPixelGetter

trait IntGenerator {
  def nextInt: (Int, IntGenerator)
}
case class SimpleRNG(seed: Long) extends IntGenerator {
  override def nextInt: (Int, IntGenerator) = {
    val newSeed = (seed * 0x5deece66dL + 0xBL) & 0xffffffffffffffffL
    val nextRng = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRng)
  }
}

case class SimpleDeterministic(start: Int, inc: Int, max: Int) extends IntGenerator {
  override def nextInt: (Int, IntGenerator) = {
    val nextInt = if (start + inc  < max) start + inc else 0
    (nextInt, SimpleDeterministic(nextInt, inc, max))
  }
}


object Generators {
  val signBit = Int.MaxValue
  def nonNegativeInt (ig: IntGenerator) : (Int, IntGenerator) = {
    val (ni, gen) = ig.nextInt
    (ni & signBit, gen)
  }

  def tripleInt(ig: IntGenerator): (Int, Int, Int, IntGenerator) = {
    val (i1, ig1) = ig.nextInt
    val (i2, ig2) = ig1.nextInt
    val (i3, ig3) = ig2.nextInt
    (i1, i2, i3, ig3)
  }

  def ints(count: Int)(ig:IntGenerator): (List[Int], IntGenerator) = {
    val init: (List[Int], IntGenerator) = (List.empty, ig)
    val (vals, nig) = (0 until count).foldLeft(init) { (a, e) =>
      val ng = a._2.nextInt
      (ng._1 :: a._1, ng._2)
    }
    (vals.reverse, nig)
  }




}