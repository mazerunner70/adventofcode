package yr2021.days
import yr2021.common.{Coordinate2D, CoordinateND, Matrix}

// see https://pages.mtu.edu/~shene/COURSES/cs3621/NOTES/geometry/geo-tran.html#euclidean


object Day19 {

  type IntMatrix = Matrix
  type Rotation = Matrix
  type PossibleRotations = Vector[IntMatrix]
  type BeaconLocation = CoordinateND
  type ScannerLocation = CoordinateND
  type ScannerLocations = Vector[ScannerLocation]
  type BeaconLocations = Vector[BeaconLocation]
  type BeaconOffset = CoordinateND
  type Translation = CoordinateND
  // TODO rewrite this to treat translation tranforms as matrices

  case class BeaconField(bps: Vector[CoordinateND]) {
    def translate(t: CoordinateND): BeaconField = BeaconField(bps.map(_-t))
    def transform(r: Matrix): Option[BeaconField] = (Matrix.parse(bps)*r).map(m=>m.toCoordinates).map(BeaconField(_))
    def transform(transformPair: TransformPair): Option[BeaconField] =
      transform(transformPair.rotation).map(_.translate(transformPair.translation))
    def intersect(other: BeaconField): Vector[CoordinateND] = bps.intersect(other.bps)
    def union(other: BeaconField): BeaconField = BeaconField((bps ++ other.bps).distinct)
  }
  case class Scan(sn: String, bf: BeaconField)
  case class TransformPair(translation: Translation, rotation: Rotation)


  def rotations(matrix: Rotation): PossibleRotations = {
    val identity = Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 1)))
    Vector.fill(3)(matrix).scanLeft(identity)((x, y)=> (x * y).get)
  }

  def getAllRotationCombinations: PossibleRotations = {
    val identity = Matrix(Vector(Vector(1,  0, 0), Vector(0, 1,  0), Vector( 0, 0, 1)))
    val rotateZ =  Matrix(Vector(Vector(0, -1, 0), Vector(1, 0,  0), Vector( 0, 0, 1)))
    val rotateY =  Matrix(Vector(Vector(0,  0, 1), Vector(0, 1,  0), Vector(-1, 0, 0)))
    val rotateX =  Matrix(Vector(Vector(1,  0, 0), Vector(0, 0, -1), Vector( 0, 1, 0)))
    val zRotations = rotations(rotateZ)
    val yRotations = rotations(rotateY)
    val xRotations = rotations(rotateX)
    val allRotations = zRotations.flatMap(zr=> {
      yRotations.flatMap(yr=> {
        val zyR = (zr * yr).get
        xRotations.map(xr => (zyR * xr).get)
      })
    })
    allRotations.distinct
  }
  val possibleRotations = getAllRotationCombinations

  def findOverlappingBeaconFields(bf1: BeaconField, bf2: BeaconField, threshold: Int): Option[(Translation, BeaconField)] =  (for {
      b1 <- bf1.bps
      b2 <- bf2.bps
      t = b2-b1
      overlap = bf2.translate(t).intersect(bf1)
      if overlap.size >= threshold
    } yield (t, BeaconField(overlap))).headOption

  def findRotatedMatch(bf1: BeaconField, bf2: BeaconField, threshold: Int): Option[(TransformPair, BeaconField)] = {
    (for {
      t <- possibleRotations
      tbf <- bf2.transform(t)
      (translation, overlap) <- findOverlappingBeaconFields(bf1, tbf, threshold)
    } yield (TransformPair(translation, t), overlap)).headOption
  }

  def mergeBeaconFields(bf1: BeaconField, bf2: BeaconField, st: TransformPair) =
    bf2.transform(st).map(_.union(bf1))

  def mergeBeaconFields(bf1: BeaconField, bf2: BeaconField, threshold: Int): Option[(BeaconField, ScannerLocation)] = (for{
    (tp, bf) <- findRotatedMatch(bf1, bf2, threshold)
    mf <- mergeBeaconFields(bf1, bf2, tp)
  } yield (mf, tp.translation)).headOption


  def attemptMerges(bf: BeaconField, ss: List[Scan], threshold: Int): (List[(Scan, BeaconField)], List[Scan], List[ScannerLocation]) = {
    val init: (List[(Scan, BeaconField)], List[Scan], List[ScannerLocation]) = (List.empty, List.empty, List.empty)
    ss.foldLeft(init)((a, e) => mergeBeaconFields(bf, e.bf, threshold) match {
      case Some((bf, sl)) => ((e, bf) ::a._1, a._2, sl::a._3)
      case None => (a._1, e :: a._2, a._3)
    })
  }

  def combineMerges(bf: BeaconField, sm: List[(Scan, BeaconField)]): BeaconField = {
    sm.foldLeft(bf)((a, e) => a.union(e._2))
  }

//  def doAllMerges(ss: List[Scan], threshold: Int, merged: BeaconField): BeaconField = ss match {
//    case Nil => merged
//    case _ => {
//      val attempt = attemptMerges(merged, ss, threshold)
//      doAllMerges(attempt._2, threshold, combineMerges(merged, attempt._1))
//    }
//  }
  def doAllMerges(ss: List[Scan], threshold: Int, merged: BeaconField, sl: List[ScannerLocation]): (BeaconField,List[ScannerLocation])  = {
    println("wefawe"+ss.size)
    if (ss.size == 0) (merged, sl) else {
      val attempt = attemptMerges(merged, ss, threshold)
      val combined = combineMerges(merged, attempt._1)
      doAllMerges(attempt._2, threshold, combined, attempt._3 ++ sl)
    }
  }

  def parseScannerText(name: String, stringLines: Vector[String]): Scan = {
    Scan (name, BeaconField(stringLines.map(line=> CoordinateND((line.split(',').map(_.toInt)).toVector))))
  }

  def maxDistance(cs: List[CoordinateND]): Int = {
    cs.map(p1=> {
      cs.map(p2=> {
        p1.manhattenDistance(p2)
      }).flatten.max
    }).max
  }



}
