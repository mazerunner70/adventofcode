package yr2021.days


import yr2021.common.{FpStream, Generators, IntGenerator, SimpleDeterministic}

import scala.annotation.tailrec
import scala.collection.mutable



object Day21 {

  type Dice = IntGenerator


  @tailrec
  def dieRoll(d: Int, c: Int, tot: Int = 0): (Int, Int) = if (c == 0)
    (tot, d) else
    dieRoll((d % 100)+1, c-1, tot +d)

  def turn(d: Dice, ps: Vector[Int], ss: Vector[Int], tc: Int): (Vector[Int], Int) = {
    if (ss.max>=1000) (ss, tc-1) else {
      val player = (tc-1) % 2
      val (dr, nd) = Generators.ints(3)(d)
      val np = ((ps(player)+dr.sum-1) % 10)+1
      println(player, dr, nd, np, ss, tc)
      turn(nd, ps.updated(player, np), ss.updated(player, ss(player)+np), tc+1)
    }
  }

  def start(ps: Vector[Int]): Int = {
    val (scores, t) = turn(SimpleDeterministic(1, 1, 100), ps, Vector(0,0), 1)
    scores(t % 2) * (t*3)
  }

  val diceRolls: Seq[Int] = for {
    d1 <- 1 to 3
    d2 <- 1 to 3
    d3 <- 1 to 3
  } yield d1+d2+d3

  var cache = new mutable.HashMap[(Int, Int, Int, Int), (Long, Long)]()

  def play2End(cp: Int, op: Int, cs: Int, os: Int): (Long, Long) = {
    val result:(Long, Long) = cache.get((cp, op, cs, os)) match {
      case Some(re) => re
      case None =>
        if (os>20)  (1, 0) else {
          val res = for {
            d <- diceRolls
            np = (cp + d -1) % 10 +1
            res = play2End(op, np, os, cs+np)
          } yield res
          val summed:(Long, Long) = res.foldLeft((0L,0L))((a, e)=> (a._1+e._2, a._2+e._1))
          println(cp, op, cs, os, summed)
          cache.put((cp, op, cs, os), summed)
          summed
        }
    }
    result
  }

}


