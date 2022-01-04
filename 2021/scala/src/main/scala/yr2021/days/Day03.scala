package yr2021.days

class Day03 {

  def getMostCommonDigits(list: List[String]): List[(String, List[Char])] =
    list.transpose.map(x=>x.groupBy(_.toString).maxBy(_._2.size))

  def onesCompliment(binaryString:String) =
    binaryString
      .replaceAll("1", "2")
      .replaceAll("0", "1")
      .replaceAll("2", "0")


  def pt1(list: List[String]) = {
    val gammaRateString = getMostCommonDigits(list).map(_._1).mkString
    val epsilonRateString = onesCompliment(gammaRateString)
    Integer.parseInt(gammaRateString, 2) * Integer.parseInt(epsilonRateString, 2)
  }

  val overrideMap = Map('0' -> -1, '1' -> 1)

  def filter(listStrings: List[String], filterPref : Char): String = {
    val matchDigit = Map(1 -> '0', 0 -> filterPref, -1 -> '1')
    def iter(list: List[String], pos: Int):String = {
      if (list.size == 1)
        list.head
      else {
        val match0Sign = math.signum(list.count(_.charAt(pos) == '0') * 2 - list.size) * overrideMap(filterPref)
        iter(list.filter(_.charAt(pos) == matchDigit(match0Sign)), pos+1)
      }
    }
    iter(listStrings, 0)
  }

  def pt2(list: List[String]) = {
    val oxygen = filter(list, '1')
    val co2 = filter(list, '0')
    Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2)
  }


}
