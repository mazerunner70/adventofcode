package yr2021.day01T10

class Day01 {

  def pt1execute(list: List[String]): Int = {
    pt1executeInt( list.map(_.toInt))
  }

  def pt1executeInt(intList: List[Int]): Int = {
    val pairedList = intList zip intList.tail
    pairedList.filter(x=>x._2-x._1>0).size
  }

  def pt1funct(list: List[String]): Int = {
    val intList = list.map(_.toInt)
    def cmp(term1: Int, term2: Int): Int = if (term2>term1) 1 else 0
    def funct1(term1: Int, term2: Int, list: List[Int], count: Int):Int = list match {
      case Nil => count + cmp(term1, term2)
      case _   => funct1(term2, list.head, list.tail, count + cmp(term1, term2))
    }
    funct1(intList.head, intList.tail.head, intList.tail.tail, 0)
  }

  def compactor(list: List[Int], compactFactor: Int, compactedList: List[Int]): List[Int] = list match {
    case l: List[Int] if (l.length>=compactFactor) => compactor(list.tail, compactFactor, list.take(compactFactor).sum :: compactedList)
    case _ => compactedList.reverse
  }

  def pt2execute(list: List[String]): Int = {
    val intList = list.map(_.toInt)
    val compactedList = compactor(intList, 3, List.empty)
    pt1executeInt(compactedList)
  }


}
