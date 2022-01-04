package yr2021.days

class Day08 {

  def pt1(lines: List[String]): Int = {
    val counts = lines.map(x=>x.dropWhile(_ != '|').split(' ').toList.filter(x=>List( 2, 4, 3, 7) contains x.length))
    counts.map(_.length).sum
  }

  /*
  Grid is:
    0000
   1    2
   1    2
   1    2
   1    2
    3333
   4    5
   4    5
   4    5
   4    5
    6666
   */

  val digit2segments = Map(0->"012456", 1->"26", 2->"02346", 3->"02356", 4->"1235", 5->"01356",
     6->"013456", 7->"025", 8->"0123456", 9->"012356")
  val segments2digits = digit2segments
    .map(e=> e._2.map(x=>(x, e._1)))
    .flatten
    .groupBy(_._1)
    .map(x=>(x._1, x._2.foldLeft(List[Int]())((a, e)=>e._2 :: a)))
  val segmentFingerprints = segments2digits.map(me=>me._2.sorted->me._1)

//  def defaultMap():Map[Int, Double]=
//    (for (j<- 0 to 9) yield (j->1.0)).toMap
//
//  val initialWeighting = (for (i <- 0 to 9) yield ((i->
//    ((for (j<- 0 to 9) yield (j->1.0)).toMap)))).toMap

//  val orderedList = digit2segments.toList.sortBy(_._2.length)

//  def swap(list: List[(Int, String)], pos1: Int, pos2: Int): List[(Int, String)] = {
//    val tmp = list(pos1)
//    list.updated(pos1, list(pos2)).updated(pos2, tmp)
//  }

  def allowedPermutations(orderedList: List[String]) = {
    val (front, back) = (orderedList.slice(0, 3), orderedList.slice(9, 10))
    orderedList.slice(3,6).permutations.map(x=>
      orderedList.slice(6,9).permutations.map(y=> front ::: x ::: y ::: back).toList).flatten.toList
  }

  val mapping = "abcdefg".zipWithIndex.toList

  def fingerprint(digits: List[String]) =
    digits.sortBy(_.length).zipWithIndex.map(x=>x._1.map(c=>(c, x._2))).flatten.groupBy(g=>g._1).map(m=>(m._1->m._2.map(_._2)))

  def findMappings(digits: List[String])= {
    fingerprint(digits).map(f=>f._1->segmentFingerprints.get(f._2))
  }
  def findMatchingFingerprint(digits: List[String]) = {
    val permutations = allowedPermutations(digits.sortBy(_.length))
    permutations.map(p=>findMappings(p))
  }


  val permutations = "abcdefg".permutations.toList

  def remap(umappedSegments: String, mapping: String) = {
    "abcdefg".zip(mapping.toUpperCase).foldLeft(umappedSegments.sorted)((a, e)=> a.replaceAll(e._1.toString, e._2.toString))
      .toLowerCase.sorted
  }

  val segmentMaps = List("abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg")

  def checkRemap(mappedSegments: String) = segmentMaps contains mappedSegments

  def checkMapping(segments: List[String], mapping: String) = {
    segments.map(s=>checkRemap(remap(s, mapping))).count(_==false) == 0
  }

  def determineMapping(segments: List[String]): String = {
    permutations.find(p=> checkMapping(segments, p)).get
  }

  def asDigit(unmappedSegments: String, mapping: String) = {
    segmentMaps.indexOf(remap(unmappedSegments, mapping))
  }

  def parseLine(line: String): List[List[String]] = {
    line.split(" \\| ").toList.map(x=>x.split(' ').toList)
  }

  def pt2(lines: List[String]) = {
    lines.map(x=> {
      val io = parseLine(x)
      val mapping = determineMapping(io(0))
      io(1).map(s=>asDigit(s, mapping)).foldLeft(0)((a,c)=>a*10+c)
    }).sum
  }
}
