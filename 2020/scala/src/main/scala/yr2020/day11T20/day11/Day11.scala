package yr2020.day11T20.day11

import yr2020.common.Util.loadList

import scala.annotation.tailrec

object Day11 {

  def extractCount1(row: Int, column: Int, grid: Array[String], rangeLimit: Int): Int = {
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

  def extractCount2(row: Int, column: Int, grid: Array[String], rangeLimit: Int): Int = {
    val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
    directions.foldLeft(0)( (acc, el) => {
      def findCell(dir: (Int, Int), magn: Int): Char = {
        val nr = magn * dir._1 + row
        val nc = magn * dir._2 + column
        if ((nr >= 0) && (nr < grid.size) && (nc >= 0) && (nc < grid(0).size) && (magn <= rangeLimit)) {
          if (grid(nr)(nc) != '.')
            grid(nr)(nc)
          else
            findCell(dir, magn + 1)
        }
        else
          '.'
      }
      acc + (if (findCell(el, 1) == '#') 1 else 0)
    })
  }

  def update(grid: Array[String], seatingLimit: Int, extractCountRule: (Int, Int, Array[String], Int) => Int, rangeLimit: Int): Array[String] = {
    (0 to grid.size-1).map {row_index =>
      val row = grid(row_index)
      val e = (0 to row.size-1).map { col_index =>
        grid(row_index)(col_index) match {
          case 'L' => if (extractCountRule(row_index, col_index, grid, rangeLimit) == 0) '#' else 'L'
          case '#' => if (extractCountRule(row_index, col_index, grid, rangeLimit) > seatingLimit) 'L' else '#'
          case x   => x
        }
      }
      e.mkString
    }.toArray
  }

  @tailrec
  def iterateTilEqual(grid: Array[String], seatingLimit: Int, extractCountRule: (Int, Int, Array[String], Int) => Int, rangeLimit: Int): Array[String] = {
    val newGrid = update(grid, seatingLimit, extractCountRule, rangeLimit)
    newGrid.foreach(println(_))
    println()
    newGrid sameElements grid match {
      case true => newGrid
      case false => iterateTilEqual(newGrid, seatingLimit, extractCountRule, rangeLimit)
    }
  }

  def countOccupied(grid: Array[String]): Int = {
    grid.foldLeft(0) ((acc, el) => acc + el.count(_ =='#'))
  }

  def part1(list: Array[String]) = {
    val finalstate = iterateTilEqual(list, 3, extractCount1, 1)
    finalstate.foreach(println(_))
    println(countOccupied(finalstate))
  }
  def part2(list: Array[String]) = {
    val finalstate = iterateTilEqual(list, 4, extractCount2, list.size)
    finalstate.foreach(println(_))
    println(countOccupied(finalstate))
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day11/input.txt").toArray
    part1(list)
    part2(list)
  }

}
