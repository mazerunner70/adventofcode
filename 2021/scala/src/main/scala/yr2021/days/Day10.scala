package yr2021.days

import yr2021.common.Util

class Day10 {

  case class BracePair(open: Char, close: Char)
  val bracePairs = List(BracePair('(', ')'), BracePair('{', '}'), BracePair('[', ']'), BracePair('<', '>'))

  def parseLine(line: String) = {

  }

  case class Result(char: Char, unclosedBraces: List[BracePair])

  def openBrace(char: Char) = bracePairs.find(bp=>bp.open == char)
  def closeBrace(char: Char) = bracePairs.find(bp=>bp.close == char)

  def braceParser(line: List[Char], openedBraces: List[BracePair] = List()): Result = line match {
    case term :: tail => {
      val open = openBrace(term)
      val close = closeBrace(term)
      (open, close) match {
        case (Some(o), None) => braceParser(tail, o :: openedBraces)
        case (None, Some(c)) => if (c == openedBraces.head)
          braceParser(tail, openedBraces.tail)
        else
          Result(term, openedBraces)
        case _ => Result(term, openedBraces)
      }
    }
    case Nil => Result(0, openedBraces)
  }
  def pt1ScoreCalculator(char:  Char): Int = List((')', 3), (']', 57), ('}', 1197), ('>', 25137)).find(_._1 == char).get._2
  def pt2ScoreCalulator(unclosedBraces: List[BracePair]): Long = {
    unclosedBraces.map(bp=>List((')', 1), (']', 2), ('}', 3), ('>', 4)).find(_._1 == bp.close).get._2)
      .foldLeft(0L)((a, v)=> a*5+v)
  }

  def parseBraces(lines: List[String]) = lines.map(line=> braceParser(line.toList))

  def pt1(lines: List[String]) = {
    parseBraces(lines).map(_ match {
      case Result(char, unclosedBraces) if char == 0 => 0
      case Result(char, unclosed) => pt1ScoreCalculator(char)
    }).sum
  }

  def pt2(lines: List[String]) = {
    val list = for (
      result <- parseBraces(lines)
      if result.char == 0
    ) yield pt2ScoreCalulator(result.unclosedBraces)
    Util.median(list.sorted)
  }


}
