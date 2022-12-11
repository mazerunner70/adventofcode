package yr2021.days

import yr2021.common.{Cell, CoordSet, CoordinateND}

import scala.annotation.tailrec
import scala.collection.mutable

object Day22 {

// Brute force
  case class Step(onOff: Boolean, box: Cuboid)

  case class Cuboid(xr: Range.Inclusive, yr: Range.Inclusive, zr: Range.Inclusive) {
    def intersect(other: Cuboid) : Option[Cuboid] = {
      def i(r1: Range.Inclusive, r2: Range.Inclusive): Option[Range.Inclusive] = {
        val xl = math.max(r1.min, r2.min)
        val xh = math.min(r1.max, r2.max)
        if (xl<=xh) Some(xl to xh) else None
      }
      (i(xr, other.xr), i(yr, other.yr), i(zr, other.zr)) match {
        case (Some(r1), Some(r2), Some(r3)) => Some(Cuboid(r1, r2, r3))
        case _ => None
      }
    }

    def toCoordinateND: Set[CoordinateND] =
      xr.flatMap(x=> yr.flatMap(y=> zr.map(z=> CoordinateND(Vector(x, y, z))))).toSet

    def volume: Long = xr.size.toLong * yr.size * zr.size
    def extract(other: Cuboid): Seq[Cuboid] = intersect(other) match {
      case None => Seq(this)
      case Some(o) => {
        var cs: mutable.ArrayBuffer[Cuboid] = mutable.ArrayBuffer.empty
        if (xr.min != o.xr.min) cs += Cuboid(xr.min to o.xr.min-1  , yr                        , zr)
        if (xr.max != o.xr.max) cs += Cuboid(o.xr.max+1 to xr.max  , yr                        , zr)
        if (yr.min != o.yr.min) cs += Cuboid(o.xr                        , yr.min to o.yr.min-1, zr)
        if (yr.max != o.yr.max) cs += Cuboid(o.xr                        , o.yr.max+1 to yr.max, zr)
        if (zr.min != o.zr.min) cs += Cuboid(o.xr                        , o.yr                      , zr.min to o.zr.min-1)
        if (zr.max != o.zr.max) cs += Cuboid(o.xr                        , o.yr                      , o.zr.max+1 to zr.max)
        cs
      }.toSeq
    }
  }

  @tailrec
  def removeOverlaps(todo: List[Step], acc: List[Step]): List[Step] = {
    if (todo.isEmpty) acc else {
      acc.find(c=>todo.head.box.intersect(c.box).isDefined) match {
        case None => removeOverlaps(todo.tail, todo.head :: acc)
        case Some(cu) => removeOverlaps(todo.head.box.extract(cu.box).map(c=>Step(todo.head.onOff, c)).toList ++todo.tail, acc)
      }
    }
  }
  def resolve(s:List[Step]): List[Step] =
    removeOverlaps(s.reverse, Nil)
  def size(ss: List[Step]): Long = {
    val e = ss.map(s=>if (s.onOff) s.box.volume else 0L)
    e.sum
  }

  def parse(line: String): Option[Step] = {
    val r = "(on|off) x=(-*\\d+)..(-*\\d+),y=(-*\\d+)..(-*\\d+),z=(-*\\d+)..(-*\\d+)".r
    line match {
      case r(onoff, xl, xh, yl, yh, zl, zh) =>
        Some(Step( (onoff == "on"), Cuboid(
          xl.toInt to xh.toInt
          , yl.toInt to yh.toInt
          , zl.toInt to zh.toInt)))
      case _ => None
    }
  }
  val boundary = Cuboid(-50 to 50, -50 to 50, -50 to 50)

  def asCells(s: Step): Set[Cell[Boolean]] =
    boundary.intersect(s.box) match {
      case Some(c) => c.toCoordinateND.map(Cell(_, s.onOff))
      case None => Set.empty
    }

  def addSteps(coordSet: CoordSet[Boolean], s: Seq[Step]) = {
    s.foldLeft(coordSet){(a, e)=>
      println(e)
      a.put(asCells(e))}
  }

  def filterTrue(coordSet: CoordSet[Boolean]): CoordSet[Boolean] = {
//    println(coordSet.cs.mkString("\n"))
    CoordSet(coordSet.cs.filter(c=>c.value))
  }





}
