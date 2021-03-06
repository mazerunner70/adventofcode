package yr2020.day11T20.day18

import fastparse.SingleLineWhitespace._
import fastparse._

object Pt2Evaluator {


  def eval(tree: (Long, Seq[(String, Long)])): Long = {
    val (base, ops) = tree
    ops.foldLeft(base){
      case (left, (op, right)) => op match{
      case "+" => left + right
      case "-" => left - right
      case "*" => left * right
      case "/" => left / right
    }}
  }

  def number[_: P]: P[Long] = P( CharIn("0-9").rep(1).!.map(_.toInt) )
  def parens[_: P]: P[Long] = P( "(" ~/ mulDiv ~ ")" )
  def factor[_: P]: P[Long] = P( number | parens )

  def addSub[_: P]: P[Long] = P( factor ~ (CharIn("+\\-").! ~/ factor).rep ).map(eval)
  def mulDiv[_: P]: P[Long] = P( addSub ~ (CharIn("*/").! ~/ addSub).rep ).map(eval)
  def expr[_: P]: P[Long]   = P( mulDiv ~ End )

  def calculate(str: String):Long = {
    val Parsed.Success(value, _) = parse(str, expr(_))
    value
  }
}
