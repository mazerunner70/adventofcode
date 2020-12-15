package yr2020.day11T20.day11

import yr2020.common.Util.loadList

object Day11 {

  def extractCount(row: Int, column: Int, grid: Array[String]): Int = {
    val rowStart = row + (if (row == 0) 0 else -1)
    val rowEnd   = row + (if (row == grid.size-1) 0 else 1)
    val colStart = column + (if (column == 0) 0 else -1)
    val colEnd = column + (if (column == grid(0).size-1) 0 else 1)

    (rowStart to rowEnd).foldLeft(0) ((r1, r) => r1 +
      (colStart to colEnd).foldLeft(0) ((c1, c) => c1 +
        (if (grid(r)(c) == '#') 1 else 0)
      )
    ) - (if (grid(row)(column) == '#') 1 else 0)
  }

  def update(grid: Array[String]): Array[String] = {
    (0 to grid.size-1).map {row_index =>
      val row = grid(row_index)
      val e = (0 to row.size-1).map { col_index =>
        grid(row_index)(col_index) match {
          case 'L' => if (extractCount(row_index, col_index, grid) == 0) '#' else 'L'
          case '#' => if (extractCount(row_index, col_index, grid) > 3) 'L' else '#'
          case x   => x
        }
      }
      e.mkString
    }.toArray
  }

  def iterateTilEqual(grid: Array[String]): Array[String] = {
    val newGrid = update(grid)
    newGrid sameElements grid match {
      case true => newGrid
      case false => iterateTilEqual(newGrid)
    }
  }

  def countOccupied(grid: Array[String]): Int = {
    grid.foldLeft(0) ((acc, el) => acc + el.count(_ =='#'))
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day11/input.txt").toArray
    val finalstate = iterateTilEqual(list)
    finalstate.foreach(println(_))
    println(countOccupied(finalstate))
  }

}
