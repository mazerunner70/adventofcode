package yr2021.days

import yr2021.common.Util

class Day07 {

  def parseInput(list:List[String]): List[Int] = list match {
    case line :: Nil => line.split(",").map(_.toInt).toList
    case _           => throw new Exception("invalid input")
  }

  // https://en.wikipedia.org/wiki/Geometric_median#Properties
  // Calculating the min diatance is a convex function, so no local minimums

  def distance(list: List[Int], element: Int): Int = {
    val sortedListIndex = list.sorted.indexWhere(e=>e >= element)
    val lowerCalc = element * sortedListIndex - list.sorted.take(sortedListIndex).sum
    val upperCalc = list.sorted.drop(sortedListIndex).sum - element * (list.size - sortedListIndex)
    lowerCalc + upperCalc
  }

  def distances(list: List[Int]): Int => Int = {
    val listSorted = list.sorted
    def pDist(position: Int) = {
      val index = listSorted.indexWhere(x=>x>=position)
      val lowerCalc = position * index - list.sorted.take(index).sum
      val upperCalc = list.sorted.drop(index).sum - position * (list.size - index)
      lowerCalc + upperCalc
    }
    pDist
  }



  def triangularDistances(list: List[Int]): Int => Int = {
    val listSorted = list.sorted
    def pDist(position: Int) = {
      val index = listSorted.indexWhere(x=>x>=position)
      listSorted.map(x=>
        if (x >= position)
          Util.triangle(x-position)
        else
          Util.triangle(position-x)
      ).sum
    }
    pDist
  }



  def pt1(list: List[String]): Int = {
    val intList = parseInput(list)
    distance( intList, Util.median(intList))
  }

  def traverseCalc(intList: List[Int]): Int = {
    def traverse(position: Int): Int = {
      val fuelCalc = triangularDistances(intList)
      val startFuelUse = fuelCalc(position)
      val nextFuelUse = fuelCalc(position+1)
      if (startFuelUse > nextFuelUse)
        traverse(position+1)
      else
        startFuelUse
    }
    traverse(1)
  }


  def pt2(list: List[String]): Int = {
    val intList = parseInput(list)
    traverseCalc(intList)
  }

}
