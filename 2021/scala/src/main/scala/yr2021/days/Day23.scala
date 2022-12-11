package yr2021.days

object Day23 {

  case class Location(id:String, dest:Option[Char])
  case class Route(from: Location, to:Location, cost: Int)

  def init[A] = {
    val destTowers = ('A' to 'D').map(t=> List(Location(t.toString+"1", Some(t)), Location(t.toString+"1", Some(t)))).flatten
    val tempShortTowers = List(Tower("AB", 1, None), Tower("BC", 1, None), Tower("CD", 1, None))
    val tempTallTowers = List(Tower("SA", 2, None), Tower("SB", 2, None))
    val
  }

}
