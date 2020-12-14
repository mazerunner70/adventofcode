package yr2020.day08

import yr2020.common.Util.loadList

import scala.annotation.tailrec

object Day08 {

  val instruction: Map[String, (Int, Map[String, Any]) => Map[String, Any]] = Map(
    "nop" -> { (param: Int, registers: Map[String, Any]) =>
      registers +(
        "programCounter"-> (registers("programCounter").asInstanceOf[Int]+1))
    },
    "acc" -> {(param: Int, registers: Map[String, Any]) =>
      registers + (
        "accumulator"-> (registers("accumulator").asInstanceOf[Int]+param),
        "programCounter"-> (registers("programCounter").asInstanceOf[Int]+1))
    },
    "jmp" -> { (param: Int, registers: Map[String, Any]) =>
      registers + (
        "programCounter"-> (registers("programCounter").asInstanceOf[Int]+param))
    }
  )

  def updateAddressHistory(registers:Map[String, Any]): Map[String, Any] =
    registers + ("addressHistory"-> (registers("programCounter").asInstanceOf[Int] :: registers("addressHistory").asInstanceOf[List[Int]]))

  @tailrec
  def execute (registers:Map[String, Any], program: List[(String, String)]): Map[String, Any] = {
    val accumulator = registers("accumulator")
    val programCounter = registers("programCounter").asInstanceOf[Int]
    val addressHistory = registers("addressHistory").asInstanceOf[List[Int]]
    val (instructionName, param) = program(programCounter)
    val operationResult = instruction(instructionName)(param.toInt, registers)
    if (addressHistory.contains(operationResult("programCounter")))
      operationResult +("result"->"1")
    else if (operationResult("programCounter") == program.size)
      operationResult + ("result"->"0")
    else
      execute(updateAddressHistory(operationResult), program)
  }

  def part1(program: List[(String, String)]): Map[String, Any] =
    execute(Map("accumulator"->0, "programCounter"->0, "addressHistory"->List()), program)

  def swapList(program: List[(String, String)]): List[(Int, String)] = {
    val indexedProgram = program.zipWithIndex
    val indexesOfNop = indexedProgram.filter(_._1._1 == "nop").map(_._2).map((_, "jmp"))
    val indexesOfJmp = indexedProgram.filter(_._1._1 == "jmp").map(_._2).map((_, "nop"))
    indexesOfNop ::: indexesOfJmp
  }

  def editProgram(program: List[(String, String)], editPoint: (Int, String) ): List[(String, String)] = {
    val array = program.toArray
    array(editPoint._1) = (editPoint._2, array(editPoint._1)._2)
    array.toList
  }


  def part2(program: List[(String, String)]): Map[String, Any] = {
    val editsList = swapList(program)
    println(f"size=${editsList.size}")
    val edit = editsList.find{edit =>
      val editedProgram = editProgram(program, edit)
      val result = part1(editedProgram)
      result("result") == "0"
    }.get
    val editedProgram = editProgram(program, edit)
    part1(editedProgram)
  }

  def main(args: Array[String]): Unit = {
    val list = loadList("day01-10/day08/input.txt")
    val program: List[(String, String)] = list.map(_.span(_ != ' ')).map(t=> (t._1, t._2.trim))
    println(part1(program))
    println(part2(program))
  }
}
