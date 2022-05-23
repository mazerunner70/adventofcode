package yr2021.common.dijkstraSP.v2

import yr2021.common.dijkstraSP.v1.IntervalTick

object DijkstraSPV2 {

  class IntervalTick2(interval: Long) {
    val start = System.currentTimeMillis()
    var iterStart = System.currentTimeMillis()
    def isTime() = System.currentTimeMillis() - iterStart > interval
    def reset = {iterStart = System.currentTimeMillis()}
    def elapsedTimeSeconds = (System.currentTimeMillis()-start)/1000
  }


  type Path[Key] = (Double, List[Key])
  type AdjacencyMap[Key] = Map[Key, List[(Double, Key)]]
  val timer = new IntervalTick(30 * 1000)

  def DijkstraSPV2[Key](lookup: Map[Key, List[(Double, Key)]], fringe: List[Path[Key]], dest: Key, visited: Set[Key]): Path[Key] = fringe match {
    case (dist, path) :: fringe_rest => path match {
      case key :: path_rest =>
        if (key == dest) (dist, path.reverse)
        else {
          val paths = lookup(key).flatMap { case (d, key) => if (!visited.contains(key)) List((dist + d, key :: path)) else Nil }
          val sorted_fringe = (paths ++ fringe_rest).sortWith { case ((d1, _), (d2, _)) => d1 < d2 }
          if (timer.isTime()) {
            println(f"Total elapsed time ${timer.elapsedTimeSeconds/60}%4.1f")
            println(f"fringe size ${fringe.size}%,d")
            println(f"Edges populated: ${visited.size}%,d out of ${lookup.size}%,d total")
            timer.reset
          }
          DijkstraSPV2(lookup, sorted_fringe, dest, visited + key)
        }
    }
    case Nil => (0, List())
  }
}