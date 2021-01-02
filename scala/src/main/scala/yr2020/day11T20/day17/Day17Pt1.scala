package yr2020.day11T20.day17

import yr2020.common.Util.loadList

object Day17Pt1 {


  def parseGrid(list: List[String], zLayer: Int): Map[(Int, Int, Int), Char] = {
    list.zipWithIndex.flatMap(y=> {
      val (line, yIndex) = y
      line.zipWithIndex.flatMap(x=> if (x._1 == '#') Some((x._2, yIndex, zLayer) -> '#') else None)
    }).toMap
  }

  def asGrid(grid: Map[(Int, Int, Int), Char], currentGridSize: ((Int, Int, Int), (Int, Int, Int))) = {
    (currentGridSize._1._3 to currentGridSize._2._3).foreach(z => {
      println(f"z=$z")
      (currentGridSize._1._2 to currentGridSize._2._2).foreach(y => {
        (currentGridSize._1._1 to currentGridSize._2._1).foreach(x => {
          print(grid.getOrElse((x, y, z), '.'))
        })
        println
      })
    })
  }

  def iteration(conway: CubicConway, grid: Map[(Int, Int, Int), Char], count: Int): Map[(Int, Int, Int), Char] = count match {
    case 0 => grid
    case _ => iteration(conway, conway.calculate(grid), count -1)
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day17/input.txt")
    val grid = Day17Pt1.parseGrid(list, 0)
    val conway = new CubicConway()
    val finalGrid = Day17Pt1.iteration(conway, grid, 6)
    val currentGridSize = conway.updateGridSize(finalGrid)
    println( Day17Pt1.asGrid(finalGrid, currentGridSize))
    println(finalGrid.size)

  }

}
