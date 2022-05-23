package yr2021.common.dijkstraSP.v1

import yr2021.common.{Vertex, Weight}

//Implementation of https://algs4.cs.princeton.edu/44sp/EdgeWeightedDigraph.java.html
final case class DirectedEdge(from: Vertex, to: Vertex, weight: Weight)
final case class EdgeWeightedDigraph(adjacency: Map[Vertex, List[DirectedEdge]] = Map.empty)

object EdgeWeightedDigraphOps {
  implicit class EdgeWeightedDigraphOps(g: EdgeWeightedDigraph) {
    def addEdge(e: DirectedEdge): EdgeWeightedDigraph = {
      val list = g.adjacency.getOrElse(e.from, List.empty)
      val adj = g.adjacency + (e.from -> (list :+ e))
      EdgeWeightedDigraph(adj)
    }
  }
}
