package yr2021.common.dijkstraSP.v3

trait DigraphMarker

/** Directed Graph
 *
 * @constructor creates a new Digraph with a number of vertices
 * @param v number of vertices
 * @see [[https://algs4.cs.princeton.edu/42directed/Digraph.java.html]]
 * @author Scala translation by Gary Struthers from Java by Robert Sedgewick and Kevin Wayne.
 */
class Digraph(v: Int) extends BaseGraphV3(v) with DigraphMarker {

  /** returns a reverse order copy */
  def reverse(): Digraph = {
    val r = new Digraph(v)
    for {
      newV <- 0 until v
      w <- adj(newV)
    } r.addEdge(w, newV)
    r
  }
}
