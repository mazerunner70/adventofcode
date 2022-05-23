package yr2021.common.dijkstraSP.v3

import scala.collection.mutable.ListBuffer

/** Common code for edge weighted graphs
 *
 * @constructor called by subclass with number of vertices
 * @tparam A BaseEdge, superclass of Edge or DirectedEdge
 * @param numV vertices in EdgeWeightedGraph or EdgeWeightedDigraph
 * @see [[https://algs4.cs.princeton.edu/43mst/EdgeWeightedGraph.java.html]]
 * @author Scala translation by Gary Struthers from Java by Robert Sedgewick and Kevin Wayne.
 */
abstract class BaseEdgeWeightedGraphV3[A <: BaseEdgeV3](val numV: Int) {
  require(numV >= 0, s"Number of vertices, v:$numV must be nonnegative")
  protected var e = 0
  protected val _adj = Array.fill[List[A]](numV)(List[A]())

  protected def buildADJ[U <: BaseEdgeWeightedGraphV3[A]](g: U): Unit = {
    e = g.e

    for {
      v <- 0 until g.numV
      e <- g.adj(v)
    } e :: _adj(v)
  }

  protected def rangeGuard(x: Int): Boolean = x match {
    case x if 0 until numV contains x => true
    case _ => false
  }

  /** returns edges incident on  v */
  def adj(v: Int): List[A] = {
    require(rangeGuard(v), s"verticies v:$v  not in 0..$numV ")
    _adj(v)
  }

  /** abstract edges in graph */
  def edges(): List[A]

  override def toString(): String = {
    val lf = sys.props("line.separator")
    val sb = new StringBuilder()
    sb append (s"$numV $e $lf")

    def addLines(v: Int) {
      sb append (s"$v : ")
      _adj(v) foreach (ed => sb append (s"$ed  "))
      sb append (lf)
    }

    for(v <- 0 until numV) addLines(v)
    sb.toString
  }
}
