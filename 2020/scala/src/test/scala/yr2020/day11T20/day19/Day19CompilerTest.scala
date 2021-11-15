package yr2020.day11T20.day19

import fastparse.{Parsed, parse}
import org.scalatest.flatspec.AnyFlatSpec
import yr2020.common.Util
import yr2020.day11T20.day19.Day19Compiler.{RuleLine, RuleNumberList, RuleNumberListOr, RuleSequence, TextChar}

class Day19CompilerTest extends AnyFlatSpec {

  behavior of "Day19CompilerTest"

  it should "ruleNumber" in {
    val Parsed.Success(value, _) = parse("12", Day19Compiler.ruleNumber(_))
    assert(value == 12)
  }
  it should "ruleNumber2" in {
    val Parsed.Success(value, _) = parse("12 2 456", Day19Compiler.ruleNumber(_))
    assert(value == 12)
  }
  it should "ruleNumberList" in {
    val Parsed.Success(value, _) = parse("12 2 456", Day19Compiler.ruleNumberList(_))
    assert(value == RuleNumberList(List(12, 2, 456)))
  }

  it should "ruleSequence" in {
    val Parsed.Success(value, _) = parse("0: 12 2 456", Day19Compiler.ruleSequence(_))
    assert(value == RuleSequence(0, RuleNumberList(List(12, 2, 456))))
  }

  it should "ruleSequenceOr" in {
    val Parsed.Success(value, _) = parse("1 3 | 3 1", Day19Compiler.ruleSequenceOr(_))
    assert(value == RuleNumberListOr(RuleNumberList(List(1, 3)), RuleNumberList(List(3, 1))))
  }

  it should "textChar" in {
    val Parsed.Success(value, _) = parse("\"a\"", Day19Compiler.textChar(_))
    assert(value == TextChar("a"))
  }

  it should "rule1" in {
    val Parsed.Success(value, _) = parse("0: 1 2", Day19Compiler.rule(_))
    assert(value == RuleLine(0, RuleNumberList(List(1, 2))))
  }
  it should "rule2" in {
    val Parsed.Success(value, _) = parse("1: \"a\"", Day19Compiler.rule(_))
    assert(value == RuleLine(1, TextChar("a")))
  }
  it should "rule3" in {
    val Parsed.Success(value, _) = parse("2: 1 3 | 3 1", Day19Compiler.rule(_))
    assert(value == RuleLine(2, RuleNumberListOr(RuleNumberList(List(1, 3)), RuleNumberList(List(3, 1)))))
  }

  it should "loadProgram" in {
    val test1 = "0: 1 2\n1: \"a\"\n2: 1 3 | 3 1\n3: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, RuleNumberList(List(1, 2))),
      1 -> RuleLine(1, TextChar("a")),
      2 -> RuleLine(2, RuleNumberListOr(RuleNumberList(List(1, 3)), RuleNumberList(List(3, 1)))),
      3 -> RuleLine(3, TextChar("b"))))
  }

  it should "execute 1-liner" in {
    val test1 = "0: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, TextChar("b"))))
    assert((true, "") == Day19Compiler.execute(program, program(0), "b"))
    assert(true == Day19Compiler.runProgram(program, "b"))
  }
  it should "execute 1-liner#2" in {
    val test1 = "0: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, TextChar("b"))))
    assert((false, "") == Day19Compiler.execute(program, program(0), "a"))
    assert(false == Day19Compiler.runProgram(program, "a"))
  }
  it should "execute 1-liner#3" in {
    val test1 = "0: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, TextChar("b"))))
    assert((true, "a") == Day19Compiler.execute(program, program(0), "ba"))
    assert(false == Day19Compiler.runProgram(program, "ba"))
  }

  it should "execute programsequence" in {
    val test1 = "0: 1 1\n1: \"a\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, RuleNumberList(List(1, 1))),
      1 -> RuleLine(1, TextChar("a"))))
    assert(Day19Compiler.execute(program, program(0), "aa") == (true, ""))
    assert(true == Day19Compiler.runProgram(program, "aa"))

    assert((true, "a") == Day19Compiler.execute(program, program(0), "aaa"))
    assert(false == Day19Compiler.runProgram(program, "aaa"))

    assert((false, "a") == Day19Compiler.execute(program, program(0), "aba"))
    assert(false == Day19Compiler.runProgram(program, "aba"))
  }

  it should "execute programsequence#2" in {
    val test1 = "0: 1 2\n1: \"a\"\n2: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, RuleNumberList(List(1, 2))),
      1 -> RuleLine(1, TextChar("a")),
      2 -> RuleLine(2, TextChar("b"))))

    assert((false, "") == Day19Compiler.execute(program, program(0), "aa"))
    assert(false == Day19Compiler.runProgram(program, "aa"))
    assert((true, "") == Day19Compiler.execute(program, program(0), "ab"))
    assert(true == Day19Compiler.runProgram(program, "ab"))
    assert((false, "a") == Day19Compiler.execute(program, program(0), "ba"))
    assert(false == Day19Compiler.runProgram(program, "ba"))
  }

  it should "execute Or" in {
    val test1 = "0: 1 2\n1: \"a\"\n2: 1 3 | 3 1\n3: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    assert(program == Map(
      0 -> RuleLine(0, RuleNumberList(List(1, 2))),
      1 -> RuleLine(1, TextChar("a")),
      2 -> RuleLine(2, RuleNumberListOr(RuleNumberList(List(1, 3)), RuleNumberList(List(3, 1)))),
      3 -> RuleLine(3, TextChar("b"))))

    assert((true, "") == Day19Compiler.execute(program, program(0), "aab"))
    assert(true == Day19Compiler.runProgram(program, "aab"))

    assert((true, "") == Day19Compiler.execute(program, program(0), "aba"))
    assert(true == Day19Compiler.runProgram(program, "aba"))
  }

  it should "execute test2" in {
    val test1 = "0: 4 1 5\n1: 2 3 | 3 2\n2: 4 4 | 5 5\n3: 4 5 | 5 4\n4: \"a\"\n5: \"b\""
    val program = Day19Compiler.loadProgram(test1.linesIterator.toList, Map())
    val list = "ababbb\nbababa\nabbbab\naaabbb\naaaabbb".split("\n").toList
    assert(Day19Compiler.filterWithProgram(list, program) == List("ababbb", "abbbab"))

  }

  it should "pass Day 1" in {
    val sections = Util.multiLineRecordParse(Util.loadList("day11-20/day19/input.txt"))
    val program = Day19Compiler.loadProgram(sections(0), Map())
    val options = sections(1)
    val valid = Day19Compiler.filterWithProgram(options, program)
    assert(valid.size == 102)
  }

  def day2RulesRewrite(map: Map[Long, RuleLine]): Map[Long, RuleLine] = {
    val editsText = "8: 42 | 42 8\n11: 42 31 | 42 11 31"
    val newmap = Day19Compiler.loadProgram(editsText.linesIterator.toList, Map())
    map ++ newmap
  }

  it should "pt2test1norewrite" in {
    val sections = Util.multiLineRecordParse(Util.loadList("day11-20/day19/test1.txt"))
    val program = Day19Compiler.loadProgram(sections(0), Map())
    val options = sections(1)
    val valid = Day19Compiler.filterWithProgram(options, program)
    assert(List("bbabbbbaabaabba", "ababaaaaaabaaab", "ababaaaaabbbaba") == valid)
  }

  it should "pt2testspec" in {
    val sections = Util.multiLineRecordParse(Util.loadList("day11-20/day19/test1.txt"))
    val program = day2RulesRewrite(Day19Compiler.loadProgram(sections(0), Map()))
    val options = List("bbabbbbaabaabba")
    val valid = Day19Compiler.filterWithProgram(options, program)
    assert(valid.size == 1)
  }

  it should "pt2test1line" in {
    val sections = Util.multiLineRecordParse(Util.loadList("day11-20/day19/test1.txt"))
    val program = day2RulesRewrite(Day19Compiler.loadProgram(sections(0), Map()))
    val options = List("bbbbbbbaaaabbbbaaabbabaaa")
    val valid = Day19Compiler.filterWithProgram(options, program)
    assert(valid.size == 1)
  }

  it should "pt2test1" in {
    val sections = Util.multiLineRecordParse(Util.loadList("day11-20/day19/test1.txt"))
    val program = day2RulesRewrite(Day19Compiler.loadProgram(sections(0), Map()))
    val options = sections(1)
    val valid = Day19Compiler.filterWithProgram(options, program)
    assert(valid.size == 12)
  }
}