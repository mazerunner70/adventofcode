package yr2020.day11T20.day17

import scalaz.Scalaz._
import scalaz._

import scala.annotation.tailrec

class QuarticConway {

  val directions = (-1 to 1).flatMap(x=> (-1 to 1).flatMap(y=> (-1 to 1).flatMap(z=> (-1 to 1).flatMap(w=>if ((x, y, z, w) == (0,0,0,0)) None else Some((x, y, z, w))))))

  val occupied = '#'

  def isOccupied(pos: (Int, Int, Int, Int), quarticGrid: Map[(Int, Int, Int, Int), Char]): Boolean = {
    //println(pos, cubicGrid.getOrElse(pos, '.'));
    quarticGrid.getOrElse(pos, '.') == '#'
  }

  @tailrec
  final def getValidPosAlongLine(positions: List[(Int, Int, Int, Int)], direction: (Int, Int, Int, Int), distance: Int, currentDistance: Int = 1) : List[(Int, Int, Int, Int)] = {
    distance match {
      case 0 => positions.init
      case _ => {
        val (x, y, z, w) = positions.head |+| direction
        getValidPosAlongLine((x, y, z, w) :: positions, direction, distance-1)
      }
    }
 }

  def countActiveNeighbours(position: (Int, Int, Int, Int), cubicGrid: Map[(Int, Int, Int, Int), Char]): Int = {
    val range = 1
    val results = directions.flatMap(dir=> {
      getValidPosAlongLine(List(position), dir, 1).map(pos => isOccupied(pos, cubicGrid))
    })
    //println(results)
    results.count(_ == true)
  }

  def updateGridSize(quarticGrid: Map[(Int, Int, Int, Int), Char], currentGridSize: ((Int, Int, Int, Int), (Int, Int, Int, Int)) = ((0,0,0,0), (0,0,0,0))):((Int, Int, Int, Int), (Int, Int, Int, Int)) = {
    quarticGrid.foldLeft(currentGridSize)((acc, entry) => updateGridSize(entry._1, acc))
  }


  def updateGridSize(pos: (Int, Int, Int, Int), currentGridSize: ((Int, Int, Int, Int), (Int, Int, Int, Int)) ): ((Int, Int, Int, Int), (Int, Int, Int, Int)) = {
    val lx = pos._1 min currentGridSize._1._1
    val ly = pos._2 min currentGridSize._1._2
    val lz = pos._3 min currentGridSize._1._3
    val lw = pos._4 min currentGridSize._1._4
    val ux = pos._1 max currentGridSize._2._1
    val uy = pos._2 max currentGridSize._2._2
    val uz = pos._3 max currentGridSize._2._3
    val uw = pos._4 max currentGridSize._2._4
    ((lx, ly, lz, lw), (ux, uy, uz, uw))
  }

  val effectRange = 1

  def calculate(quarticGrid: Map[(Int, Int, Int, Int), Char]): Map[(Int, Int, Int, Int), Char] = {
    val currentGridSize = updateGridSize(quarticGrid)
    val newGrid = {
    (currentGridSize._1._1-effectRange to currentGridSize._2._1+effectRange).flatMap(x=>
      (currentGridSize._1._2-effectRange to currentGridSize._2._2+effectRange).flatMap(y=>
        (currentGridSize._1._3-effectRange to currentGridSize._2._3+effectRange).flatMap(z=> {
          (currentGridSize._1._4-effectRange to currentGridSize._2._4+effectRange).flatMap(w=> {
            val neighbourCount = countActiveNeighbours((x, y, z, w), quarticGrid)
            val positionOccupied = isOccupied((x, y, z, w), quarticGrid)
            neighbourCount match {
              case 2 | 3 if positionOccupied => Some((x, y, z, w) -> '#')
              case 3 if !positionOccupied => Some((x, y, z, w) -> '#')
              case _ => None
            }
          })
        })
      )
    )
    }
    println(newGrid)
    newGrid.toMap
  }

}
