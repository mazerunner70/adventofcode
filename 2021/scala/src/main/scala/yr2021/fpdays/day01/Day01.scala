package yr2021.fpdays.day01

import yr2021.fpcommon.PuzzleInput.{asIntSeq, loadFromFile}

class Day01 {

  case class Depths(seq: Seq[Int])

  def depthIncrease(delta: Int): Option[Int] =
    if (delta>0) Some(delta) else None

  def getDepthPairs(depths: Depths): Seq[(Int, Int)] =
    depths.seq zip depths.seq.tail

  def depthTriples(depths: Depths): Iterator[Int] =
    depths.seq.sliding(3).map(_.sum)

  def getDepthChanges(depthPairs: Seq[(Int, Int)]): Seq[Int] =
    depthPairs.map{case (d1, d2) => d2 - d1}

  def pt1Depths(lines: Seq[String]) =
    Depths(asIntSeq(lines))
  def pt2Depths(lines: Seq[String]) =
    Depths(depthTriples(Depths(asIntSeq(lines))).toSeq)

  def getCount(depths: Depths) = {
    val increases = for {
      depthChange <- (getDepthPairs _ andThen getDepthChanges)(depths)
      increased <- depthIncrease(depthChange)
    } yield increased
    increases.size
  }

  def pt1(lines: Seq[String]) = {
    val depths = pt1Depths(lines)
    getCount(depths)
  }
  def pt2(lines: Seq[String]) = {
    val depths = pt2Depths(lines)
    getCount(depths)
  }



}
