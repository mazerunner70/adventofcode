package yr2020.day11T20.day17
import scalaz._
import Scalaz._

import scala.annotation.tailrec

class CubicConway {

  val directions = (-1 to 1).flatMap(x=> (-1 to 1).flatMap(y=> (-1 to 1).flatMap(z=>if ((x, y, z) == (0,0,0)) None else Some((x, y, z)))))

  val occupied = '#'

  def isOccupied(pos: (Int, Int, Int), cubicGrid: Map[(Int, Int, Int), Char]): Boolean = {
    //println(pos, cubicGrid.getOrElse(pos, '.'));
    cubicGrid.getOrElse(pos, '.') == '#'
  }

  @tailrec
  final def getValidPosAlongLine(positions: List[(Int, Int, Int)], direction: (Int, Int, Int), distance: Int, currentDistance: Int = 1) : List[(Int, Int, Int)] = {
    distance match {
      case 0 => positions.init
      case _ => {
        val (x, y, z) = positions.head |+| direction
        getValidPosAlongLine((x, y, z) :: positions, direction, distance-1)
      }
    }
 }

  def countActiveNeighbours(position: (Int, Int, Int), cubicGrid: Map[(Int, Int, Int), Char]): Int = {
    val range = 1
    val results = directions.flatMap(dir=> {
      getValidPosAlongLine(List(position), dir, 1).map(pos => isOccupied(pos, cubicGrid))
    })
    //println(results)
    results.count(_ == true)
  }

  def updateGridSize(cubicGrid: Map[(Int, Int, Int), Char], currentGridSize: ((Int, Int, Int), (Int, Int, Int)) = ((0,0,0), (0,0,0))):((Int, Int, Int), (Int, Int, Int)) = {
    cubicGrid.foldLeft(currentGridSize)((acc, entry) => updateGridSize(entry._1, acc))
  }


  def updateGridSize(pos: (Int, Int, Int), currentGridSize: ((Int, Int, Int), (Int, Int, Int)) ): ((Int, Int, Int), (Int, Int, Int)) = {
    val lx = pos._1 min currentGridSize._1._1
    val ly = pos._2 min currentGridSize._1._2
    val lz = pos._3 min currentGridSize._1._3
    val ux = pos._1 max currentGridSize._2._1
    val uy = pos._2 max currentGridSize._2._2
    val uz = pos._3 max currentGridSize._2._3
    ((lx, ly, lz), (ux, uy, uz))
  }

  val effectRange = 1

  def calculate(cubicGrid: Map[(Int, Int, Int), Char]): Map[(Int, Int, Int), Char] = {
    val currentGridSize = updateGridSize(cubicGrid)
    val newGrid = {
    (currentGridSize._1._1-effectRange to currentGridSize._2._1+effectRange).flatMap(x=>
      (currentGridSize._1._2-effectRange to currentGridSize._2._2+effectRange).flatMap(y=>
        (currentGridSize._1._3-effectRange to currentGridSize._2._3+effectRange).flatMap(z=> {
          val neighbourCount = countActiveNeighbours((x, y, z), cubicGrid)
          val positionOccupied = isOccupied((x, y, z), cubicGrid)
          neighbourCount match {
            case 2 | 3 if  positionOccupied => Some((x, y, z) -> '#')
            case     3 if !positionOccupied => Some((x, y, z) -> '#')
            case _ => None
          }
        })
      )
    )
    }
    println(newGrid)
    newGrid.toMap
  }

}
