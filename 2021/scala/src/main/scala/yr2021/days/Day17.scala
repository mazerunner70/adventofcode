package yr2021.days

object Day17 {

  case class Coordinate(x: Int, y: Int) {
    def + (velocity: Velocity) =
      Coordinate(x+velocity.x, y+velocity.y)
  }
  case class Velocity(x: Int, y: Int)
  case class Box(c1: Coordinate, c2: Coordinate) {
    def top: Int = math.max(c1.y, c2.y)
    def bottom: Int = math.min(c1.y, c2.y)
    def left: Int = math.min(c1.x, c2.x)
    def right: Int = math.max(c1.x, c2.x)
    def topLeft: Coordinate = Coordinate(left, top)
    def bottomRight: Coordinate = Coordinate(right, bottom)
    def inside(coordinate: Coordinate): Boolean =
      (left to right contains coordinate.x) &&
      (bottom to top contains coordinate.y)
    def inside(box: Box): Boolean =
      (left to right contains box.c1.x) &&
        (bottom to top contains box.c1.y) &&
        (left to right contains box.c2.x) &&
        (bottom to top contains box.c2.y)
  }

  def probeDeltaV(velocity: Velocity): Velocity =
    Velocity(
      velocity.x - math.signum(velocity.x),
      velocity.y - 1
    )

  def rangeDecision(startCoord: Coordinate, currentCoord: Coordinate, targetArea: Box): Int = {
    val beyondTargetArea = if (startCoord.x < targetArea.left)
      currentCoord.x > targetArea.right else currentCoord.x < targetArea.left
    if (targetArea.left to targetArea.right contains currentCoord.x)
      0
    else
      if (beyondTargetArea) 1 else -1
  }

  def beyondTarget(currentCoord: Coordinate, currentVelocity: Velocity, targetArea: Box): Boolean =
    (currentVelocity.x == 0 && !(targetArea.left to targetArea.right contains currentCoord.x)) ||
      (currentCoord.y < targetArea.bottom && currentVelocity.y < 0)


  def trajectory(startPosition: Coordinate, startVelocity: Velocity, targetArea: Box): List[Coordinate] = {
    def step(newPosition: Coordinate, newVelocity: Velocity, coords: List[Coordinate]): List[Coordinate] = {
      if (targetArea.inside(newPosition) || beyondTarget(newPosition, newVelocity, targetArea))
        (newPosition :: coords).reverse
      else
        step(newPosition + newVelocity, probeDeltaV(newVelocity), newPosition :: coords)
    }
    step(startPosition, startVelocity, Nil)
  }

  case class ProbeOutcome(success:Boolean, highestY:Int, lastCoord: Coordinate, rangDecision: Int)

  def launchProbe(startPosition: Coordinate, startVelocity: Velocity, targetArea: Box): ProbeOutcome = {
    val traj = trajectory(startPosition, startVelocity, targetArea)
    val endpoint = traj.last
    val highestY = traj.map(_.y).max
    val decision = rangeDecision(startPosition, endpoint, targetArea)
    ProbeOutcome(targetArea.inside(endpoint), highestY, endpoint, decision)
  }

  def tryFindHeights(xVel: Int, startPosition: Coordinate, targetArea: Box): (Int, List[Velocity]) = {
    val maxY =  1-targetArea.bottom
    def run(yVel: Int, listHeights: List[Int], listStartV: List[Velocity]): (Int, List[Velocity]) = {
      val startV = Velocity(xVel, yVel)
       (launchProbe(startPosition, startV, targetArea), yVel) match {
        case (_, y) if y == maxY=>  (listHeights.max, listStartV)
        case (ProbeOutcome(true, highestY, _, _), _) => run (yVel+1, highestY :: listHeights, startV :: listStartV)
        case (ProbeOutcome(false, _, _, 1), _) => (listHeights.max, listStartV)
        case (ProbeOutcome(false, _, _, _), _) => run (yVel+1, listHeights, listStartV)
      }
    }
    run(targetArea.bottom, List(0), Nil)
  }

  def iterateX(startPosition: Coordinate, targetArea: Box): (Int, Seq[Velocity]) = {
    val minX = (math.sqrt(targetArea.left*2 +0.25)-0.5).toInt
    val (heights,lists) = (minX to targetArea.right).map(tryFindHeights(_, startPosition, targetArea)).unzip
    (heights.max, lists.flatten)
  }
}
