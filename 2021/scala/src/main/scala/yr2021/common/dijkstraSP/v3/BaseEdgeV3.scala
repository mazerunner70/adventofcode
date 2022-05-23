package yr2021.common.dijkstraSP.v3

/** Common code for edge, directed edge
 *
 * @constructor called by subclass with both vertices and a weight
 * @param v edge of vertex
 * @param w edge of vertex
 * @param weight of edge
 * @see [[https://algs4.cs.princeton.edu/43mst/Edge.java.html]]
 * @see [[https://algs4.cs.princeton.edu/44sp/DirectedEdge.java.html]]
 * @author Scala translation by Gary Struthers from Java by Robert Sedgewick and Kevin Wayne.
 */
abstract class BaseEdgeV3(v: Int, w: Int, val weight: Double) {
  require(v >= 0 && w >= 0 && !weight.isNaN, "s invalid arg(s) v:$v w:$w weight$weight")

  override def toString: String = f"$v%d-$w%d $weight%.5f "
}
