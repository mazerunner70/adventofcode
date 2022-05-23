package yr2021.common.dijkstraSP.v3

import scala.annotation.tailrec

/** Solves for shortest path from a source where edge weights are non-negative
 *
 * @constructor creates a new DijkstraSP with an edge weighted digraph and source vertex
 * @param g acyclic digraph, edges have direction and weight
 * @param s source vertex
 * @see [[https://algs4.cs.princeton.edu/44sp/DijkstraSP.java.html]]
 * @author Scala translation by Gary Struthers from Java by Robert Sedgewick and Kevin Wayne.
 */
class DijkstraSPV3(g: EdgeWeightedDigraphV3, s: Int) {
  require(g.edges forall (_.weight >= 0))
  private val _distTo = Array.fill[Double](g.numV)(Double.PositiveInfinity)
  _distTo(s) = 0.0
  private val edgeTo = new Array[DirectedEdgeV3](g.numV)
  private val pq = new IndexMinPQV3[Double](g.numV)
  relaxVertices()

  private def relaxVertices() {

    def relax(e: DirectedEdgeV3) {
      val v = e.from
      val w = e.to
      if (_distTo(w) > _distTo(v) + e.weight) {
        _distTo(w) = _distTo(v) + e.weight
        edgeTo(w) = e
        if (pq.contains(w)) pq.decreaseKey(w, _distTo(w)) else pq.insert(w, _distTo(w))
      }
    }

    @tailrec
    def loop() {
      if (!pq.isEmpty) {
        val v = pq.delMin
        g.adj(v) foreach (e => relax(e))
        loop()
      }
    }

    pq.insert(s, _distTo(s))
    loop()
  }

  /** returns length of shortest path from source to v */
  def distTo(v: Int): Double = _distTo(v)

  /** returns if there is a path from source to v */
  def hasPathTo(v: Int): Boolean = _distTo(v) < Double.PositiveInfinity

  /** returns path from source to v if it exists */
  def pathTo(v: Int): Option[List[DirectedEdgeV3]] = {
    if (!hasPathTo(v)) None else {

      @tailrec
      def loop(e: DirectedEdgeV3, path: List[DirectedEdgeV3] ): List[DirectedEdgeV3] = {
        if(e != null) loop(edgeTo(e.from), e :: path) else path
      }
      val path = loop(edgeTo(v), List[DirectedEdgeV3]())
      Some(path)
    }
  }
}
