package yr2021.common.dijkstraSP.v3

/** Weighted edge.
 *
 * @constructor creates a DirectedEdge with a start and end vertices and a weight
 * @param v edge of vertex
 * @param w edge of vertex
 * @param weight of edge
 * @see [[https://algs4.cs.princeton.edu/44sp/DirectedEdge.java.html]]
 * @author Scala translation by Gary Struthers from Java by Robert Sedgewick and Kevin Wayne.
 */
class DirectedEdgeV3(v: Int, w: Int, weight: Double) extends BaseEdgeV3(v, w, weight) with BaseDirectedEdge {

  /** returns vertex at head of directed edge */
  def from(): Int = v

  /** returns vertex at tail of directed edge */
  def to(): Int = w
}
