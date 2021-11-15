package yr2020.day11T20

package object day14 {
  def executeLines(lines: List[String], memory: Map[Long, Long], mask: String, commands: Map[String, (String, Map[Long, Long], String)=> (Map[Long, Long], String)]): (Map[Long, Long], String) = lines match {
    case h :: t => {
      val command = h.substring(0, 3)
      val (newMemory, newMask) = commands(command)(h, memory, mask)
      executeLines(t, newMemory, newMask, commands)
    }
    case Nil => (memory, mask)
  }


}
