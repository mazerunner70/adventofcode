package yr2021.days

import yr2021.common.CoordinateND

object Day19V2 {

  case class PointPair(pt1: CoordinateND, pt2: CoordinateND) {
    def d:Double = pt1.distance(pt2).get
  }

  case class DistanceProfile(pps: List[PointPair])



}
