package yr2021.days

import yr2021.common.dijkstraSP.v1.EdgeWeightedDigraphOps._
import yr2021.common.dijkstraSP.v1.{DijkstraSP, DirectedEdge, EdgeWeightedDigraph}
import yr2021.common.dijkstraSP.v2.DijkstraSPV2
import yr2021.common.dijkstraSP.v2.DijkstraSPV2.AdjacencyMap
class Day15 {

  // This is am example of "single-pair shortest path problem"
  // The standard solution is to use "Dijkstra's algorithm"
  // discussed here: https://algs4.cs.princeton.edu/44sp/
type Day15Grid = List[List[Int]]

  def parseGrid(lines: List[String]): Day15Grid =
    lines.map(line=>line.map(c=>c.asDigit).toList)

  def adjacents(x: Int, y: Int, xSize: Int, ySize: Int): List[(Int, Int)] = {
    List((x+1, y),(x, y+1),(x-1, y),(x, y-1)).filter{case (x, y) => x< xSize && x>=0 && y<ySize && y>=0}
  }

  def asAdjacencyId(x: Int, y: Int, xSize: Int) = x+y*xSize

  def asEdgeWeightedDigraph(grid: Day15Grid) : EdgeWeightedDigraph = {
    val xSize = grid(0).size
    val ySize = grid.size
    val edgeWeightedDigraph = EdgeWeightedDigraph()
    val directedEdges = for (
      x <- 0 until xSize;
      y <- 0 until ySize;
      adj <- adjacents(x, y, xSize, ySize)
     ) yield DirectedEdge(asAdjacencyId(x, y, xSize),asAdjacencyId(adj._1, adj._2, xSize), grid(adj._2)(adj._1))
    directedEdges.foldLeft(edgeWeightedDigraph)((a, e)=>a.addEdge(e))
  }

  def calculateRoute(day15Grid: Day15Grid, toVertex: Int): Either[String, Double] = {
    val edgeWeightedDigraph = asEdgeWeightedDigraph(day15Grid)
    val shortestPathCalc = DijkstraSP.run(edgeWeightedDigraph, 0)
    shortestPathCalc.distToV(toVertex)
  }

  def pt1(lines: List[String], toVertex: Int): Either[String, Double] = {
    val grid = parseGrid(lines)
    calculateRoute(grid, toVertex)
  }
  def pt2(lines: List[String], toVertex: Int): Either[String, Double] = {
    val grid = pt2CalculateGrid(lines)
    calculateRoute(grid, toVertex)
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


  def v2AsAdjacency(grid: Day15Grid): AdjacencyMap[Int] = {
    val xSize = grid(0).size
    val ySize = grid.size
    val edgeWeightedDigraph = EdgeWeightedDigraph()
    val directedEdges = for (
      x <- 0 until xSize;
      y <- 0 until ySize
    ) yield (asAdjacencyId(x, y, xSize)->adjacents(x, y, xSize, ySize).map(adj=>(grid(adj._2)(adj._1).toDouble, asAdjacencyId(adj._1, adj._2, xSize))))
    directedEdges.toMap
  }

  def v2Pt1(lines: List[String], toVertex: Int): (Double, List[Int]) = {
    val grid = parseGrid(lines)
    val adjMap = v2AsAdjacency(grid)
    DijkstraSPV2.DijkstraSPV2(adjMap, List((0.0, List(0))), toVertex, Set())
  }

}
