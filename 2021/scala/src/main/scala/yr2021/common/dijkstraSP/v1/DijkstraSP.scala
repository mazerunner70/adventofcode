package yr2021.common.dijkstraSP.v1

import yr2021.common.{Distance, Vertex}

import java.lang.System.nanoTime
import scala.collection.mutable

class DijkstraSP {

}
class IntervalTick(interval: Long) {
  val start = System.currentTimeMillis()
  var iterStart = System.currentTimeMillis()
  def isTime() = System.currentTimeMillis() - iterStart > interval
  def reset = {iterStart = System.currentTimeMillis()}
  def elapsedTimeSeconds = (System.currentTimeMillis()-start)/1000
}

object Timer {
  val intervalPrint = new IntervalTick(30 * 1000)
  var count = 0
  var timeSpentNs = 0L
  intervalPrint.reset
  def timer(t: => Any) = {
    val start = nanoTime()
    val func = t
    val end = nanoTime()
    count += 1; timeSpentNs += end-start
    if (intervalPrint.isTime()) {
      println(f" In 30s, timeaverage: ${timeSpentNs/count}%,d ns, (count: $count, total time: ${timeSpentNs/1000/1000}%,d ms)")
      count = 0; timeSpentNs = 0; intervalPrint.reset
    }
    func
  }
}

object DijkstraSP {
  def run(edgeWeightedDigraph: EdgeWeightedDigraph, startVertex: Vertex): ShortestPathCalc = {
    if (edgeWeightedDigraph.adjacency.find(e=>e._2.find(e1=>e1.weight<0.0).isDefined).isDefined)
      throw new Exception("negative weights not allowed")

    val maxVertex = calcMaxVertex(edgeWeightedDigraph)//Get the highest vertex Id to initalise buffers with
    val edgeTo = mutable.ArrayBuffer.fill[Option[DirectedEdge]](maxVertex+1)(None) // the shortest edge to a vertex
    val distanceTo = mutable.ArrayBuffer.fill(maxVertex+1)(Double.PositiveInfinity) // the distance to this vertex (from startVertex)

    //init source distance and add to the queue
    distanceTo(startVertex) = 0.0 // Essentially the distance to start vertex (from start vertex) is zero
    val sourceDist = (startVertex, distanceTo(startVertex))
    // set up a priority queue that retrieves the entry with the shortest distance
    val sortByDistance: Ordering[(Vertex, Distance)] = (a, b) => a._2.compareTo(b._2)
    val queue = mutable.PriorityQueue[(Vertex, Distance)](sourceDist)(sortByDistance)
    val timer = new IntervalTick(30 * 1000)
    while (queue.nonEmpty) {
      val (minimumDistanceVertex: Int, _) = Timer.timer(queue.dequeue())
      val edges = edgeWeightedDigraph.adjacency.getOrElse(minimumDistanceVertex, List.empty)

      edges.foreach { e =>
        if (distanceTo(e.to) > distanceTo(e.from) + e.weight) { // If a shorter route to the destination is found
          distanceTo(e.to) = distanceTo(e.from) + e.weight
          edgeTo(e.to) = Some(e)
          if (!queue.exists(_._1 == e.to)) queue.enqueue((e.to, distanceTo(e.to)))
        }
      }
      if (timer.isTime()) {
        println(f"Total elapsed time ${timer.elapsedTimeSeconds/60.0}%4.1f minutes")
        println(f"queue size: ${queue.size}%s")
        println(f"Edges populated: ${edgeTo.count(_.isDefined)}%,d out of ${edgeTo.size}%,d total")
        timer.reset
      }
    }
    new ShortestPathCalc(edgeTo.toSeq, distanceTo.toSeq)
  }
  def calcMaxVertex(edgeWeightedDigraph: EdgeWeightedDigraph): Int =
    edgeWeightedDigraph.adjacency.map(e=>e._1).max
  def isVertexInDigraph(edgeWeightedDigraph: EdgeWeightedDigraph, vertex: Int) = {
    if (calcMaxVertex(edgeWeightedDigraph) < vertex || vertex < 0)
      throw new Exception("vertex must be in digraph")
  }

  /**
   *
   * @param edgeTo a sequence which represents the last edge
   *               on the shortest path from 'sourceV' to vertex i.
   *               None means there is no path to vertex i
   * @param distTo a sequence of distances from source vertex to a specific i vertex
   */
  class ShortestPathCalc(edgeTo: Seq[Option[DirectedEdge]], distTo: Seq[Double]) {
    /**
     *
     * @param destinationVertex vertex to get the path for
     * @return returns error when vertex is invalid or sequence of edges
     * which form the path from source vertex to vertex
     */
    def pathTo(destinationVertex: Vertex): Either[String, Seq[DirectedEdge]] = {

      def go(list: List[DirectedEdge], vv: Int): List[DirectedEdge] =
        edgeTo(vv) match {
          case Some(e) => go(e +: list, e.from)
          case None => list
        }

      hasPath(destinationVertex).map(b => if (!b) Seq() else go(List(), destinationVertex))
    }

    /**
     *
     * @param destinationVertex vertex to check whether path from source vertex to v vertex exists
     * @return returns error when v is invalid or Boolean
     * when some path from source vertex to vertex v exists
     */
    def hasPath(destinationVertex: Int): Either[String, Boolean] =
      distTo.lift(destinationVertex).map(_ < Double.PositiveInfinity).toRight(s"Vertex $destinationVertex does not exist")

    /**
     *
     * @param v vertex to get the distance for
     * @return returns error when v is invalid or
     * the Double distance which is a sum of weights
     */
    def distToV(v: Int): Either[String, Double] =
      distTo.lift(v).toRight(s"Vertex $v does not exist")
  }

}