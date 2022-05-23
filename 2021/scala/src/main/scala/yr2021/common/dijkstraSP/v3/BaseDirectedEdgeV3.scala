package yr2021.common.dijkstraSP.v3

/** Common trait for DirectedEdge, FlowEdge
 *
 * Differentiate directed edge vertices from vertices in undirected edge
 *
 * @see [[https://algs4.cs.princeton.edu/44sp/DirectedEdge.java.html]]
 * @see [[https://algs4.cs.princeton.edu/64maxflow/FlowEdge.java.html]]
 * @author Scala translation by Gary Struthers from Java by Robert Sedgewick and Kevin Wayne.
 */
trait BaseDirectedEdge{

  /** start vertex of directed edge */
  def from():Int

  /** end vertex of directed edge */
  def to():Int
}
