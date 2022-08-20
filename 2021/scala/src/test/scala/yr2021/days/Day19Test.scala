package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.{CoordinateND, InputData, Matrix}
import yr2021.days.Day19.{BeaconField, Scan, ScannerLocation, TransformPair}

class Day19Test extends AnyFlatSpec {

  it should "BeaconField" in {
    val coords = BeaconField(Vector(CoordinateND(Vector(1, 2, 3, 4)), CoordinateND(Vector(1, 2, 3, 4))))
    val scan = Scan("s1", coords)
    assert(scan.bf == coords)
  }

  it should "rotations" in {
    val rotateZ =  Matrix(Vector(Vector(0, -1, 0), Vector(1, 0,  0), Vector( 0, 0, 1)))
    assert( Day19.rotations(rotateZ) == Vector(
      Matrix(Vector(Vector(1, 0, 0), Vector(0, 1, 0), Vector(0, 0, 1))),
      Matrix(Vector(Vector(0, -1, 0), Vector(1, 0, 0), Vector(0, 0, 1))),
      Matrix(Vector(Vector(-1, 0, 0), Vector(0, -1, 0), Vector(0, 0, 1))),
      Matrix(Vector(Vector(0, 1, 0), Vector(-1, 0, 0), Vector(0, 0, 1)))))
  }

  it should "getAllRotationCombinations" in {
    val allRot = Day19.getAllRotationCombinations
    assert(allRot.distinct.size == 24)
  }

  it should "scannerOverlap" in {
    val scannerA = Scan("sA", BeaconField(Vector(CoordinateND(Vector(0, 2)),CoordinateND(Vector(4, 1)),CoordinateND(Vector(3, 3)))))
    val scannerB = Scan("sB", BeaconField(Vector(CoordinateND(Vector(-1, -1)),CoordinateND(Vector(-5, 0)),CoordinateND(Vector(-2, 1)))))
    val identity = Matrix(Vector(Vector(1, 0), Vector(0, 1)))
    val (t, v) = Day19.findOverlappingBeaconFields(scannerA.bf, scannerB.bf, 3).get
    assert(t == CoordinateND(Vector(-5, -2)))
  }

  it should "rotateMatch" in {
    val scans = InputData.fromFile("days/day19/test2.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl=>Day19.parseScannerText(bl.head,bl.tail.toVector))
    val scan1 = scans(0)
    scans.tail.foreach(s=>{
      val (t, vb) = Day19.findRotatedMatch(scan1.bf, s.bf, 6).get
      assert(vb == scan1.bf)
    })
  }
  it should "rotateMatch2" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl=>Day19.parseScannerText(bl.head,bl.tail.toVector))
    val (t, vb) = Day19.findRotatedMatch(scans(0).bf, scans(1).bf, 12).get
    val tar1 = BeaconField("""-618,-824,-621
                   |-537,-823,-458
                   |-447,-329,318
                   |404,-588,-901
                   |544,-627,-890
                   |528,-643,409
                   |-661,-816,-575
                   |390,-675,-793
                   |423,-701,434
                   |-345,-311,381
                   |459,-707,401
                   |-485,-357,347""".stripMargin.split('\n').map(line=> CoordinateND((line.split(',').map(_.toInt)).toVector)).toVector)
    assert(vb == tar1)
    assert(t.translation == CoordinateND(Vector(-68, 1246, 43)))

    val tbf1 = scans(1).bf.transform(t).get
    val (t4, vb14) = Day19.findRotatedMatch(tbf1, scans(4).bf, 12).get
    val tar4 = BeaconField("""459,-707,401
                             |-739,-1745,668
                             |-485,-357,347
                             |432,-2009,850
                             |528,-643,409
                             |423,-701,434
                             |-345,-311,381
                             |408,-1815,803
                             |534,-1912,768
                             |-687,-1600,576
                             |-447,-329,318
                             |-635,-1737,486""".stripMargin.split('\n').map(line=> CoordinateND((line.split(',').map(_.toInt)).toVector)).toVector.sorted)
    assert(vb14.bps.sorted == tar4.bps)
    assert(t4.translation == CoordinateND(Vector(20, 1133, -1061)))
  }

  def getScanField(bf1: BeaconField, bf2: BeaconField):BeaconField = {
    val m = Day19.findRotatedMatch(bf1, bf2, 12).get._1
    bf2.transform(m).get
  }
  def getMergedField(bf1: BeaconField, bf2: BeaconField):(BeaconField, ScannerLocation) = {
    Day19.mergeBeaconFields(bf1, bf2, 12).get
  }


  it should "rotateMatch4" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl => Day19.parseScannerText(bl.head, bl.tail.toVector))
    val sc1Field = getScanField(scans(0).bf, scans(1).bf)
    val (t, vb) = Day19.findRotatedMatch(sc1Field, scans(3).bf, 12).get
    assert(vb.bps.size == 12)
    assert(t.translation == CoordinateND(Vector(92, 2380, 20)))
    val mf = getMergedField(scans(0).bf, scans(1).bf)
    val (mt, mvb) = Day19.findRotatedMatch(sc1Field, scans(3).bf, 12).get
    assert(mvb.bps.size == 12)
    assert(mt.translation == CoordinateND(Vector(92, 2380, 20)))

  }

  it should "manual merge" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl => Day19.parseScannerText(bl.head, bl.tail.toVector))
    val m1 = getMergedField(scans(0).bf, scans(1).bf)
    val m2 = getMergedField(m1._1, scans(3).bf)
    val m3 = getMergedField(m2._1, scans(4).bf)
    val m4 = getMergedField(m3._1, scans(2).bf)
    assert(m4._1.bps.size == 79)
    assert(Day19.maxDistance(m1._2::m2._2::m3._2::m4._2::Nil) == 3621)
  }
  it should "attemptmerges" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl => Day19.parseScannerText(bl.head, bl.tail.toVector))
    val results = Day19.attemptMerges(scans(0).bf, scans.tail, 12)
    assert (results._1.map(_._1.sn) == List("--- scanner 1 ---"))
    assert (results._2.map(_.sn) == List("--- scanner 4 ---", "--- scanner 3 ---", "--- scanner 2 ---"))
  }

  it should "combineMerges" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl => Day19.parseScannerText(bl.head, bl.tail.toVector))
    val results = Day19.attemptMerges(scans(0).bf, scans.tail, 12)
    val combined = Day19.combineMerges(scans(0).bf, results._1)
    assert(combined.bps.size == 38)
  }

  it should "attemptMerges2" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl => Day19.parseScannerText(bl.head, bl.tail.toVector))
    val r1 = Day19.attemptMerges(scans(0).bf, scans.tail, 12)
    val c1 = Day19.combineMerges(scans(0).bf, r1._1)
    val r2 = Day19.attemptMerges(c1, r1._2, 12)
    assert (r2._1.map(_._1.sn) == List("--- scanner 3 ---", "--- scanner 4 ---"))
    assert (r2._2.map(_.sn) == List("--- scanner 2 ---"))
    val c2 = Day19.combineMerges(c1, r2._1)
    val r3 = Day19.attemptMerges(c2, r2._2, 12)
    assert (r3._1.map(_._1.sn) == List("--- scanner 2 ---"))
    assert (r3._2.map(_.sn) == List())
    val c3 = Day19.combineMerges(c2, r3._1)
    assert(c3.bps.size == 79)
    assert(Day19.maxDistance(r3._3++r2._3++r1._3) == 3621)
  }


  it should "mergeBeaconFields" in {
      val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
        .map(bl=>Day19.parseScannerText(bl.head,bl.tail.toVector))
      val bf01 = Day19.mergeBeaconFields(scans(0).bf, scans(1).bf, 12).get
      assert(bf01._1.bps.size == 38)
  }

  it should "doAllMerges" in {
    val scans = InputData.fromFile("days/day19/test.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl=>Day19.parseScannerText(bl.head,bl.tail.toVector))
    val result = Day19.doAllMerges(scans.tail, 12, scans.head.bf, Nil)
    assert(result._1.bps.size == 79)
    assert(Day19.maxDistance(result._2) == 3621)
  }

  it should "part1" in {
    val scans = InputData.fromFile("days/day19/input.txt").multiLineRecordParse(InputData.emptyLineSeparater)
      .map(bl=>Day19.parseScannerText(bl.head,bl.tail.toVector))
    val result = Day19.doAllMerges(scans.tail, 12, scans.head.bf, Nil)
    assert(result._1.bps.size == 454)
    assert(Day19.maxDistance(result._2) == 6)
  }




}
