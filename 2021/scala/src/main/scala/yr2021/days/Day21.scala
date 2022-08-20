package yr2021.days


import yr2021.common.{FpStream, IntGenerator}

import scala.annotation.tailrec



object Day21 {

  type Dice = IntGenerator

  @tailrec
  def dieRoll(d: Int, c: Int, tot: Int = 0): (Int, Int) = if (c == 0)
    (tot, d) else
    dieRoll((d % 100)+1, c-1, tot +d)

  def turn(d: Int, ps: Vector[Int], ss: Vector[Int], tc: Int): (Vector[Int], Int) = {
    if (ss.max>=1000) (ss, tc-1) else {
      val player = (tc-1) % 2
      val (dr, nd) = dieRoll(d, 3)
      val np = ((ps(player)+dr-1) % 10)+1
      println(player, dr, nd, np, ss, tc)
      turn(nd, ps.updated(player, np), ss.updated(player, ss(player)+np), tc+1)
    }
  }
  def start(ps: Vector[Int]): Int = {
    val (scores, t) = turn(1, ps, Vector(0,0), 1)
    scores(t % 2) * (t*3)
  }



}


