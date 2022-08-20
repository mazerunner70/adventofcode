package yr2021.days
import yr2021.common.{Coordinate2D, Grid}



class Day11 {

  def incEnergy(grid: Grid): Grid = {
    def incTransformer(coordinate: Coordinate2D, neighbours: Seq[Coordinate2D]) =
      grid.get(coordinate) + 1
    grid.transformGrid(Seq(), incTransformer)
  }

  def locateFlashers(grid: Grid, flashers: Seq[Coordinate2D] = Seq()): StepOutcome = {
    def flashTransform(coordinate: Coordinate2D, flashersInt: Seq[Coordinate2D]) = {
      val count = grid.get(coordinate) + flashers.count(c=>c.isAdjacent(coordinate))
      count
    }

    val newGrid = grid.transformGrid(flashers, flashTransform)
    val newFlashers = newGrid.filter(c=>newGrid.get(c)>9)
    if (newFlashers == flashers)
      StepOutcome(newGrid, flashers)
    else locateFlashers(grid, newFlashers)
  }
  def resetFlashers(grid: Grid): Grid = {
    def flashTransform(coordinate: Coordinate2D, neighbours: Seq[Coordinate2D]) =
      if (grid.get(coordinate)<10) grid.get(coordinate) else 0
    grid.transformGrid(Seq(), flashTransform)
  }
  case class StepOutcome(grid: Grid, flashers: Seq[Coordinate2D])
  def step(grid: Grid): StepOutcome = {
    val incEnergyGrid = incEnergy(grid)
    val energisedGrid = locateFlashers(incEnergyGrid)
    val flashedGrid = resetFlashers(energisedGrid.grid)
    StepOutcome(flashedGrid, energisedGrid.flashers)
  }

  def pt1(lines: List[String], count: Int): Int = {
    val startGrid = Grid.asGrid(lines,Grid.parseLineString2Int)
    def round(grid: Grid, flashes: Int, countDown: Int): Int = countDown match {
      case 0 => flashes //+ step(grid).flashers.size
      case c => {
        val outcome = step(grid)
        round(outcome.grid, outcome.flashers.size+flashes, countDown-1)
      }
    }
    round(startGrid, 0, count)
  }

  def pt2(lines: List[String]): Int = {
    val startGrid = Grid.asGrid(lines,Grid.parseLineString2Int)
    def round(grid: Grid, flashes: Int = 0, countUp: Int = 0): Int = flashes == grid.size match {
      case true => countUp
      case c => {
        val outcome = step(grid)
        round(outcome.grid, outcome.flashers.size, countUp + 1)
      }
    }
    round(startGrid)
  }

}
