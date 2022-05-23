package fpbook.ch06

trait RNG {
  def nextInt: (Int, RNG)
}


case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5deece66dL + 0XbL) & 0xffffffffffffL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}

object RNG {
  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)


  def nonNegativeEven: Rand[Int] =
    map(nonNegativeInt)(i=> i - i % 2)

  def double2: Rand[Double] =
    map(nonNegativeInt)(n=>n/(Int.MaxValue.toDouble+1))

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (n, newSeed) = rng.nextInt
    val newN = if (n<0) -(n+1) else n
    (newN, newSeed)
  }
  def double(rng: RNG): (Double, RNG) = {
    val (n, newSeed) = nonNegativeInt(rng)
    (n/(Int.MaxValue.toDouble+1), newSeed)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (n, newSeed) = nonNegativeInt(rng)
    val (n1, newSeed2) = double(newSeed)
    ((n, n1), newSeed2)
  }
  def randIntDouble: Rand[(Int, Double)] =
    map2(nonNegativeInt, double2)((a, b)=> (a, b))

  def ints(count: Int)(rng:RNG): (List[Int], RNG) = {
    def inner(count: Int, rng: RNG, result: List[Int]): (List[Int], RNG) = count match {
      case 0 => (result, rng)
      case _ => {
        val (n, newSeed) = nonNegativeInt(rng)
        inner(count -1, newSeed,  n :: result)
      }
    }
    inner(count, rng, List())
  }

  def both[A](ra: Rand[A], rb:Rand[A]): Rand[List[A]] =
    map2(ra, rb)((a1, a2)=>List(a1, a2))


  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =
    fs.foldRight(unit(List[A]()))((f, acc)=> map2(f, acc)(_ :: _))

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  def map2[A, B, C](ra: Rand[A], rb:Rand[B])(f: (A, B)=>C): Rand[C] = {
    rng=> {
      val (na, rng2) = ra(rng)
      val (nb, rng3) = rb(rng2)
      (f(na, nb), rng3)
    }
  }

  def map2F[A, B, C](ra: Rand[A], rb:Rand[B])(f: (A, B)=>C): Rand[C] = {
    flatMap(ra)(a=>mapF(rb)(b=>f(a, b)))
  }

  def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
    rng => {
      val (a, r1) = f(rng)
      g(a)(r1) // We pass the new state along
    }

  def nonNegativeLessThan(n: Int): Rand[Int] = {
    flatMap(nonNegativeInt) { i =>
      val mod = i % n
      if (i + (n-1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
    }
  }

   def mapF[A, B](s: Rand[A])(f: A => B): Rand[B] =
     flatMap(s)(a=>unit(f(a)))



}
