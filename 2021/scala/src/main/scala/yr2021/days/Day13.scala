package yr2021.days
import yr2021.common.{Coordinate2D, Util}
class Day13 {

  case class MirrorLine(position: Int, axis: String)
  object MirrorLine {
    def apply(params: Seq[String]): MirrorLine = MirrorLine(params(1).toInt, params(0))
  }
  def parseCoordinate(line: String): Coordinate2D = Coordinate2D(line.split(",").map(_.toInt))
  def parseMirrorRule(line: String): MirrorLine = MirrorLine(line.split(" ")(2).split("="))

  def reflectLine(reflectPosition: Int, coord: Int): Int = reflectPosition + (reflectPosition - coord)
  def reflect(coordinate: Coordinate2D, mirrorLine: MirrorLine): Coordinate2D = mirrorLine.axis match {
    case "x" => Coordinate2D(reflectLine(mirrorLine.position, coordinate.x), coordinate.y)
    case "y" => Coordinate2D(coordinate.x, reflectLine(mirrorLine.position, coordinate.y))
  }

  def mirrorGroup(coordinate: Coordinate2D, mirrorLine: MirrorLine): String = mirrorLine.axis match {
    case "x" => if (coordinate.x > mirrorLine.position) "mirror" else "not"
    case "y" => if (coordinate.y > mirrorLine.position) "mirror" else "not"
  }

  def transform(coordinates: List[Coordinate2D], mirrorLine: MirrorLine): List[Coordinate2D] = {
    val grouped = coordinates.groupBy(c=>mirrorGroup(c, mirrorLine))
    grouped("not") ++ grouped("mirror").map(c=>reflect(c, mirrorLine))
  }

  def pt1(lines: List[String]): Int = {
    val blocks = Util.multiLineRecordParse(lines)
    val coordinates = blocks(0).map(parseCoordinate(_))
    val mirrorLines = blocks(1).map(parseMirrorRule(_))
    val transformedCoords = transform(coordinates, mirrorLines(0)).sortBy(c=>(c.x, c.y))
    transformedCoords.distinct.size
  }
  def pt2(lines: List[String]): List[Coordinate2D] = {
    val blocks = Util.multiLineRecordParse(lines)
    val coordinates = blocks(0).map(parseCoordinate(_))
    val mirrorLines = blocks(1).map(parseMirrorRule(_))
    val transformedCoords = mirrorLines.foldLeft(coordinates)((coords, ml)=> transform(coords, ml).distinct)
    transformedCoords
  }

}
