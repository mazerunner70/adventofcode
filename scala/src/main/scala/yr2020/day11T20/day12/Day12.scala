package yr2020.day11T20.day12

import yr2020.common.Util.loadList

import scala.annotation.tailrec

object Day12 {

  def mod360(angle: Int) : Int = (angle+360) % 360

  val directions: Map[Int, (Int, Int)] = Map (
      0 -> ( 0,  1),
     90 -> ( 1,  0),
    180 -> ( 0, -1),
    270 -> (-1,  0)
  )

  val rules1: Map[String, (Int, Int, Int, Int) => (Int, Int, Int)] = Map(
    "N" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x, y + delta, direction)
    },
    "S" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x, y - delta, direction)
    },
    "E" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x + delta, y, direction)
    },
    "W" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x - delta, y, direction)
    },
    "L" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x, y , mod360(direction - delta))
    },
    "R" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x, y , mod360(direction + delta))
    },
    "F" -> { (x: Int, y: Int, direction: Int, delta: Int) =>
      (x + directions(direction)._1*delta, y  + directions(direction)._2*delta, direction)
    },
  )

  val rules2: Map[String, (Int, Int, Int, (Int, Int)) => (Int, Int, (Int, Int))] = Map(
    "N" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) =>
      (x, y + delta, shipPos)
    },
    "S" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) =>
      (x, y - delta, shipPos)
    },
    "E" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) =>
      (x + delta, y, shipPos)
    },
    "W" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) =>
      (x - delta, y, shipPos)
    },
    "L" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) => {
      val newDir = directions(mod360(-delta))
      ( x * newDir._2 + y * newDir._1,
        y * newDir._2 - x * newDir._1,
        shipPos)     }
    },
    "R" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) => {
      val newDir = directions(mod360(delta))
      ( x * newDir._2 + y * newDir._1,
        y * newDir._2 - x * newDir._1,
        shipPos)    }
    },
    "F" -> { (x: Int, y: Int, delta: Int, shipPos:(Int, Int)) =>
      ( x, y, (shipPos._1+x*delta, shipPos._2+y*delta))
    },
  )

  @tailrec
  def iterate1(instructionList: List[(String, Int)], x: Int, y: Int, direction: Int): (Int, Int) = {
    instructionList match {
      case Nil                 => (x, y)
      case instruction :: _    => {
        val (newX, newY, newDir) = rules1(instruction._1)(x, y, direction, instruction._2)
        iterate1(instructionList.tail, newX, newY, newDir )
      }
    }
  }

  @tailrec
  def iterate2(instructionList: List[(String, Int)], x: Int, y: Int, shipPos:(Int, Int)): (Int, Int) = {
    instructionList match {
      case Nil                 => shipPos
      case instruction :: _    => {
        val (newX, newY, newShipPos) = rules2(instruction._1)(x, y, instruction._2, shipPos)
        println(instructionList.head, newX, newY, newShipPos)
        iterate2(instructionList.tail, newX, newY, newShipPos)
      }
    }
  }

  def part1(list: List[String]) = {
    val instructions = list.map(line=>(line.substring(0, 1), line.substring(1).toInt))
    val (x, y) = iterate1(instructions, 0, 0, 90)
    println(x.abs+y.abs)
  }

  def part2(list: List[String]) = {
    val instructions = list.map(line=>(line.substring(0, 1), line.substring(1).toInt))
    val (x, y) = iterate2(instructions, 10, 1, (0,0))
    println(x.abs+y.abs)
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day12/input.txt")
    part1(list)
    part2(list)
  }

}
