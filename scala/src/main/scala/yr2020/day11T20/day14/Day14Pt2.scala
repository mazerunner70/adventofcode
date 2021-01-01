package yr2020.day11T20.day14

import yr2020.common.Util.loadList

import scala.annotation.tailrec

object Day14Pt2 {

  val assign = raw"""mem\[(\d+)\] = (\d+)""".r


  def getAllAddresses(mask: String): List[Long] = {
    val xCount = mask.count(_=='X')
    val options = 1 << xCount
    val maskAsList = mask.toCharArray.toList
    (0 to options).map{ x=>
      applyBinaryToMask(maskAsList, x.toBinaryString.reverse.padTo(xCount, '0').reverse.toCharArray.toList, List() )
    }.toList
  }
  @tailrec
  def applyBinaryToMask(mask: List[Char], binary: List[Char], value: List[Char]): Long = mask match {
    case h :: t => {
      val (bit, newBinary) = if (h == 'X')
        (binary.head, binary.tail)
      else
        (h, binary)
      applyBinaryToMask(t, newBinary, bit :: value)
    }
    case Nil    => {
       BigInt(value.reverse.mkString, 2).longValue
    }
  }

  def operations: Map[String, (String, Map[Long, Long], String)=> (Map[Long, Long], String)] = Map(
    "mas" -> { (line: String, memory: Map[Long, Long], mask: String) =>
      (memory, line.substring(7))
    },
    "mem" -> { (line: String, memory: Map[Long, Long], mask: String) =>
      line match {
        case assign(address, value) => {
          val addressUpdates = getAllAddresses(mask).map {lockedMask=>
            val blankingMask = BigInt(mask
              .replaceAll("1", "T")
              .replaceAll("0", "1")
              .replaceAll("T", "0")
              .replaceAll("X", "0"),2).longValue
            (((address.toLong & blankingMask) | lockedMask) -> value.toLong)
          }.toMap
          (memory ++ addressUpdates, mask)
        }
      }
    }
  )

  def getResult(memory: Map[Long, Long]) =
    memory.map(_._2).sum

  def main(args: Array[String]): Unit = {
    val list = loadList("day11-20/day14/input.txt")

    println(getResult(executeLines(list, Map(), "", operations)._1))
  }

}
