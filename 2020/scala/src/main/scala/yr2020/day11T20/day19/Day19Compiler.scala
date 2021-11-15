package yr2020.day11T20.day19

import fastparse._
import NoWhitespace._

object Day19Compiler {

  sealed trait RuleType
  case class RuleSequence(ruleId: Long, sequence: RuleNumberList)
  case class RuleNumberList(list: Seq[Long]) extends RuleType
  case class RuleNumberListOr(left: RuleNumberList, right: RuleNumberList) extends RuleType
  case class TextChar(char: String) extends RuleType
  case class RuleLine(id: Long, rule: RuleType)

  def ruleNumber[_: P]: P[Long] = P( CharIn("0-9").rep(1).!.map(_.toLong)  )
  def ruleNumberList[_: P]: P[RuleNumberList] = P( ruleNumber.rep(min = 1, sep = " ").map(RuleNumberList(_)))
  def ruleSequence[_: P]: P[RuleSequence] = P ((ruleNumber ~ ": " ~ ruleNumberList).map(rs=>RuleSequence(rs._1, rs._2)))
  def ruleSequenceOr[_: P]: P[RuleNumberListOr] = P( ruleNumberList ~ " | " ~ ruleNumberList).map(rs=>RuleNumberListOr(rs._1, rs._2))

  def textChar[_: P]: P[TextChar] = P( "\"" ~ CharIn("a-z").!.map(TextChar(_)) ~ "\""  )

  def factor[_: P]: P[RuleType] = P( ruleSequenceOr | ruleNumberList | textChar ~ End )

  def rule[_: P]: P[RuleLine] = P( ruleNumber ~ ": " ~  factor ).map(rs=>RuleLine(rs._1, rs._2))

  def loadProgram(lines: List[String], parsed: Map[Long, RuleLine]): Map[Long, RuleLine] = lines match {
    case h :: t => parse(h, Day19Compiler.rule(_)) match {
      case Parsed.Success(value, _) => loadProgram(t, parsed + (value.id -> value))
    }
    case Nil => parsed
  }

  def runProgram(program: Map[Long, RuleLine], text: String): Boolean = {
    val (success, newHead) = execute(program, program(0), text, List())
    success && (newHead.size == 0)
  }

  def execute(program: Map[Long, RuleLine], ruleLine: RuleLine, text: String, depth: List[RuleLine] = List()):(Boolean, String) = {
    def doRuleSequence(list: Seq[Long], cText: String):(Boolean, String) = {
      list match {
        case h :: t => {
          val (success, newHead) = execute(program, program(h), cText, ruleLine::depth)
          if (success) doRuleSequence(t, newHead) else (false, newHead)
        }
        case Nil => (true, cText)
      }
    }

    def doRuleOr(left: RuleNumberList, right: RuleNumberList):(Boolean, String) = {
      val (successLeft, newHeadLeft) = doRuleSequence(left.list, text)
      val (successRight, newHeadRight) = doRuleSequence(right.list, text)
      if (successLeft)
        (true, newHeadLeft)
      else if (successRight)
        (true, newHeadRight)
      else
        (false, newHeadLeft)
    }
    def infiniteLoopDetected(checkDepth: Int = 10):Boolean =
      depth.size>=checkDepth &&(depth.take(checkDepth) == List.fill(checkDepth)(depth.head))

//    println(depth.size, depth)
    if (text.size >0) {
      ruleLine.rule match {
        case TextChar(char: String) => (char(0) == text.head, text.tail)
        case RuleNumberList(list: Seq[Long]) => doRuleSequence(list, text)
        case RuleNumberListOr(left: RuleNumberList, right: RuleNumberList) => doRuleOr(left, right)
      }
    }
    else {
      println(text.size)
      (false, text)
    }

  }

  def filterWithProgram(stringList: List[String], program: Map[Long, RuleLine]): List[String] = {
    stringList.map(string => if (runProgram(program, string)) Some(string) else None).flatten
  }

}
