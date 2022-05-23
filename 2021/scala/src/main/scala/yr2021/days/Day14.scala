package yr2021.days

import yr2021.common.Util

class Day14 {
  type MatchText = String
  type InsertText = String
  type InsertStructure = Seq[(MatchText, InsertText)]
  case class InsertRule(matchText: MatchText, insert: InsertText)
  object InsertRule { def apply(strings: List[String]): InsertRule = InsertRule(strings(0), strings(1))}

  def parseRules(lines: List[String]) =
    lines.map(line=> InsertRule(line.split(" -> ").toList))

  def createInsertStructure(string: String): InsertStructure =
    (for(i<- 1 to string.length-1) yield string.slice(i-1, i+1)).map(s=>(s, ""))


  def insertCalculator(rules: List[InsertRule], struct: InsertStructure): InsertStructure = rules match {
    case Nil => struct
    case rule :: _ => {
      val newStruct = struct.map(s => if (rule.matchText == s._1) (s._1, rule.insert) else s)
      insertCalculator(rules.tail, newStruct)
    }
  }
  def reassembleString(struct: InsertStructure) : String = {
    struct.map(s=>s._1(0) + s._2).mkString + struct.last._1(1)
  }

  def steps(polymer: String, rules: List[InsertRule], counter: Int):String = counter match {
    case 0 => polymer
    case _ => {
      val struct = createInsertStructure(polymer)
      val newStruct = insertCalculator(rules, struct)
      val newPolymer = reassembleString(newStruct)
      steps(newPolymer, rules, counter -1)
    }
  }

  def pt1raw(lines: List[String], count: Int) = {
    val blocks = Util.multiLineRecordParse(lines)
    val startPolymer = blocks(0).head
    val insertRules = parseRules(blocks(1))
    steps(startPolymer, insertRules, count)
  }

  def pt1(lines: List[String], count: Int) = {
    val polymer = pt1raw(lines, count)
    val distribution = polymer.groupBy(e => e).map(e => e._2.size)
    distribution.max - distribution.min
  }



  type PolymerUnitName = String
  case class PolymerUnit(name: PolymerUnitName, frequency: Long) {
    def + (other: PolymerUnit): PolymerUnit =
      if (name == other.name) PolymerUnit(name, frequency+other.frequency) else this
  }
  case class PolymerEdits(adds: List[PolymerUnit], subtracts: List[PolymerUnit]) {
    def + (insertNames: List[String], matched: PolymerUnit): PolymerEdits =
      PolymerEdits(adds ::: insertNames.map(p=>PolymerUnit(p, matched.frequency)), PolymerUnit(matched.name, -matched.frequency) :: subtracts)
  }
  case class V3InsertRule(matchText: PolymerUnitName, newUnits: List[PolymerUnitName])
  object V3InsertRule {
    def apply(strings: List[String]): V3InsertRule = {
      V3InsertRule(strings(0),
        List(strings(0)(0) + strings(1), strings(1) + strings(0)(1) ))
    }
  }
  def pt2ParseRules(lines: List[String]): List[V3InsertRule] =
    lines.map(line=> V3InsertRule(line.split(" -> ").toList))

  def mergePolymerUnits(A: List[PolymerUnit], B: List[PolymerUnit]): List[PolymerUnit] = {
          val merged = A ++ B
          val grouped = merged.groupBy(_.name)
          val cleaned = grouped.map{e=>
                    val le:Seq[PolymerUnit] = e._2
                    (e._1->le.map(_.frequency).sum)}
          cleaned.map(pu=>PolymerUnit(pu._1, pu._2)).toList
  }

  case class Polymer(polymerUnits: List[PolymerUnit], lastChar: Char) {
    def + (polymerEdits: PolymerEdits): Polymer =
      this.+(polymerEdits.subtracts ++ polymerEdits.adds)
    def + (delta: List[PolymerUnit]): Polymer =
      Polymer(mergePolymerUnits(polymerUnits, delta), lastChar)
    def find(polymerUnitName: PolymerUnitName):Option[PolymerUnit] =
      polymerUnits.find(pu=>pu.name == polymerUnitName)
    def contains(polymerUnitName: PolymerUnitName): Boolean =
      find(polymerUnitName).isDefined
    def charDistribution: Map[Char, Long] = {
      polymerUnits.groupBy(pu=>pu.name(0)).map(e=>(e._1, e._2.map(pu=>pu.frequency).sum))
      .map(pu=>(pu._1-> (if (pu._1 == lastChar) pu._2+1 else pu._2))).toMap
    }
  }
  object Polymer {
    def apply(line:String): Polymer =
      Polymer(Util.distributionMap(line.sliding(2).toList).map(e=>PolymerUnit(e._1, e._2)).toList, line.last)
  }

  def pt2CalculateEdits(rules: List[V3InsertRule], polymer: Polymer, polymerEdits: PolymerEdits = PolymerEdits(List(), List())): PolymerEdits= rules match {
    case Nil => polymerEdits
    case rule :: _ if polymer contains rule.matchText =>
      pt2CalculateEdits( rules.tail, polymer, polymerEdits + (rule.newUnits,
        PolymerUnit(rule.matchText, polymer.find(rule.matchText).get.frequency)))
    case _ => pt2CalculateEdits(rules.tail, polymer, polymerEdits)
  }

  def pt2Steps(polymer: Polymer, count: Int, rules: List[V3InsertRule]): Polymer = count match {
    case 0 => polymer
    case _ => pt2Steps(polymer + pt2CalculateEdits(rules, polymer), count-1,rules)
  }

  def pt2raw(lines: List[String], count: Int) = {
    val blocks = Util.multiLineRecordParse(lines)
    val startPolymer = Polymer(blocks(0).head)
    val insertRules = pt2ParseRules(blocks(1))
    val newPolymer = pt2Steps(startPolymer, count,insertRules)
    val dist = newPolymer.charDistribution
    dist
  }
  def pt2(lines: List[String], count: Int) = {
    val distribution = pt2raw(lines, count)
    distribution.values.max - distribution.values.min
  }


}
