package yr2020.day11T20.day14

import yr2020.common.Util.loadList

object Day14Pt1 {

  val assign = raw"""mem\[(\d+)\] = (\d+)""".r
  def operations: Map[String, (String, Map[Long, Long], String)=> (Map[Long, Long], String)] = Map(
    "mas" -> { (line: String, memory: Map[Long, Long], mask: String) =>
      (memory, line.substring(7))
    },
    "mem" -> { (line: String, memory: Map[Long, Long], mask: String) =>
      line match {
        case assign(address, value) => {
          val blankingMask = BigInt(mask.replaceAll("1", "0").replaceAll("X", "1"),2).longValue
          val editmask = BigInt(mask.replaceAll("X", "0"),2).longValue
          (memory + (address.toLong -> ((value.toLong & blankingMask) | editmask)), mask)
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
