package yr2021.days

import yr2021.common.dijkstraSP.v3.{DijkstraSPV3, DirectedEdgeV3, EdgeWeightedDigraphV3}

// Code taken from https://github.com/garyaiki/Scala-Algorithms

class Day15V3 {

  type Day15Grid = List[List[Int]]

  def parseGrid(lines: List[String]): Day15Grid =
    lines.map(line=>line.map(c=>c.asDigit).toList)

  def adjacents(x: Int, y: Int, xSize: Int, ySize: Int): List[(Int, Int)] = {
    List((x+1, y),(x, y+1),(x-1, y),(x, y-1)).filter{case (x, y) => x< xSize && x>=0 && y<ySize && y>=0}
  }

  def asAdjacencyId(x: Int, y: Int, xSize: Int) = x+y*xSize

  def asEdgeWeightedDigraph(grid: Day15Grid) : EdgeWeightedDigraphV3 = {
    val xSize = grid(0).size
    val ySize = grid.size
    val edgeWeightedDigraph = new EdgeWeightedDigraphV3(xSize*ySize)
    val directedEdges = for (
      x <- 0 until xSize;
      y <- 0 until ySize;
      adj <- adjacents(x, y, xSize, ySize)
    ) yield new DirectedEdgeV3(asAdjacencyId(x, y, xSize),asAdjacencyId(adj._1, adj._2, xSize), grid(adj._2)(adj._1))
    for(ed <- directedEdges) edgeWeightedDigraph.addEdge(ed)
    edgeWeightedDigraph
  }

  def pt1(lines: List[String], toVertex: Int): Double = {
    val grid = parseGrid(lines)
    val edgeWeightedDigraphV3 = asEdgeWeightedDigraph(grid)
    val dijkstraSPV3 = new DijkstraSPV3(edgeWeightedDigraphV3, 0)
    dijkstraSPV3.distTo(toVertex)
  }

  def pt2(lines: List[String], toVertex: Int): Double = {
    val grid = pt2CalculateGrid(lines)
    val edgeWeightedDigraphV3 = asEdgeWeightedDigraph(grid)
    val dijkstraSPV3 = new DijkstraSPV3(edgeWeightedDigraphV3, 0)
    dijkstraSPV3.distTo(toVertex)
  }

  def incrementGrid(day15Grid: Day15Grid): Day15Grid = {
    day15Grid.map(row=> row.map(x=>(x % 9)+1))
  }

  def attachGrid(accum: Day15Grid, grid: Day15Grid): Day15Grid = {
    val start = if (accum.isEmpty) List.fill(grid.size)(List()) else accum
    (start zip grid).map{case (a, b)=>a++b}
  }

  def attachGridsLeft(gridList: List[Day15Grid], count: Int, accum: Day15Grid): Day15Grid = (gridList, count) match {
    case (_, 0) => accum
    case (grid :: t, _) => attachGridsLeft(t,count -1,attachGrid(accum,grid))
  }

  def attachGridsDown(gridList: List[Day15Grid], count: Int, accum: Day15Grid): Day15Grid = (gridList, count) match {
    case (_, 0) => accum
    case (_ :: t, _) => attachGridsDown(t, count-1, accum ++ attachGridsLeft(gridList, 5, List()))
  }
  def identity(day15Grid: Day15Grid): Day15Grid = day15Grid
  def pt2CalculateGrid(lines: List[String]): Day15Grid = {
    val grid = parseGrid(lines)
    val grids = (0 until  9).foldLeft(List(grid))((a, _)=>{ incrementGrid(a.head)::a}).reverse
    val superGrid = attachGridsDown(grids, 5, List())
    superGrid
  }


}
