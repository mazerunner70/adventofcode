package yr2021.days
case class Cavern(name: String) {
  def isBig = name == name.toUpperCase
}
case class Connection(fromCavern: Cavern, destCavern:Cavern)

class Day12 {


  def parseLine(line: String): Seq[String] =
    line.split("-").toSeq

  def createCavernNameLookup(cavernConnections: Seq[Seq[String]]): Map[String, Cavern] =
    cavernConnections.flatten.toSet.map((nm: String) =>nm->Cavern(nm)).toMap

  def getConnections(cavernConnections: Seq[Seq[String]]) = {
    val cavernLookup = createCavernNameLookup(cavernConnections)
    cavernConnections.map(p=>Connection(cavernLookup.get(p(0)).get, cavernLookup.get(p(1)).get)) ++
      cavernConnections.map(p=>Connection(cavernLookup.get(p(1)).get, cavernLookup.get(p(0)).get))
  }

  def onwardConnections(paths: Seq[Connection]): Map[Cavern, Seq[Connection]] =
    paths.groupBy(path=>path.fromCavern)

  type OnwardConnections = Map[Cavern, Seq[Connection]]
  type TraversedCaverns = List[Cavern]
  type VisibleOnwardCaverns = List[Cavern]
  type AllowedCaverns = List[Cavern]

  def visibleOnwardCaverns(cavern: Cavern, onwardConnections: OnwardConnections): VisibleOnwardCaverns =
    onwardConnections.get(cavern).get.map(_.destCavern).asInstanceOf[VisibleOnwardCaverns]

  def pt1OnwardCavernFilter(traversed: TraversedCaverns, visibleCaverns: VisibleOnwardCaverns):AllowedCaverns = {
    visibleCaverns.filter(c=>c.isBig || !traversed.contains(c))
  }
  def pt2MaxVisits(cavern: Cavern, traversed: TraversedCaverns): Int =
    if (List("start", "end").contains(cavern.name)) 1 else 2 - pt2CountSecondVisits(traversed)
  def pt2CountSecondVisits(traversed: TraversedCaverns): Int = {
//    println(traversed);
    traversed.filterNot(t=>t.isBig).groupBy(c=>c).map(cm=>cm._2.size-1).sum}

  def pt2OnwardCavernFilter(traversed: TraversedCaverns, visibleCaverns: VisibleOnwardCaverns):AllowedCaverns = {
    val allowedCaverns = visibleCaverns.filter(c=>c.isBig || traversed.count(t=>t == c) < pt2MaxVisits(c, traversed))
    allowedCaverns
  }

  def calculateTraversals(onwardOptions: OnwardConnections, cavernFilter: (TraversedCaverns, VisibleOnwardCaverns)=>AllowedCaverns): Seq[TraversedCaverns] = {
    val startNode = onwardOptions.keySet.find(_.name == "start").get
    val endNode = onwardOptions.keySet.find(_.name == "end").get
    def traverse(traversed: TraversedCaverns): List[TraversedCaverns] = {
      val allowedOnward = cavernFilter(traversed, visibleOnwardCaverns(traversed.head, onwardOptions))
      val travelled = allowedOnward.foldLeft(List[List[Cavern]]())((a, e)=>traverse(e :: traversed) ::: a)
      if (traversed.head == endNode)
        traversed :: travelled
      else
        travelled
    }
    traverse(List(startNode))
  }

  def pt1(lines:List[String]): Seq[Seq[Cavern]] = {
    val namePairs = lines.map(parseLine(_))
    val routes = onwardConnections(getConnections(namePairs))
    val traversals = calculateTraversals(routes, pt1OnwardCavernFilter)
    traversals.map(_.reverse)
  }

  def pt2(lines:List[String]): Seq[Seq[Cavern]] = {
    val namePairs = lines.map(parseLine(_))
    val routes = onwardConnections(getConnections(namePairs))
    val traversals = calculateTraversals(routes, pt2OnwardCavernFilter)
    traversals.map(_.reverse)
  }


}
