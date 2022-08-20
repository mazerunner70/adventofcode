package yr2021.days

import yr2021.common.{Coordinate2D, Grid}

object Day20 {

  case class Iea(config: Vector[Int]) {
    def getPixelValue(cells: Seq[Int]): Int = {
      val index: Int = cells.foldLeft(0)((a, e)=> (a<<1)+e)
      config(index)
    }
  }

  val deltas = (-1 to 1).flatMap(y=> (-1 to 1).map(x=> Coordinate2D(x, y)))

  def transform(grid: Grid, iea: Iea, ip: Int): Grid = {
    val bg = grid.grow(ip).grow(ip)
    def transformer(center: Coordinate2D, neighbours: Seq[Coordinate2D]): Int = {
      if (bg.onEdge(center))
        iea.getPixelValue(Vector.fill(9)(ip))
      else
        iea.getPixelValue(neighbours.map(bg.get(_)))
    }
    bg.transformGrid(deltas, transformer)
  }

  def transformSequence(grid: Grid, iea: Iea, count: Int) : Grid = {
    val init: (Grid, Int) = (grid, 0)
    (1 to count).foldLeft(init)((a, e)=> {
      (transform(a._1, iea,a._2),
      iea.getPixelValue(Vector.fill(9)(a._2)))
    })._1
  }

  def countOn(grid: Grid) = {
    def isOn(c: Coordinate2D) = grid.get(c) == 1
    grid.filter(isOn).size
  }

}
