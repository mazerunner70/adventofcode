package yr2022.days

import yr2022.common.InputData

object Day01 {

  case class CalorieList(line: List[String]) {
    def total: Int = line.map(_.toInt).sum
  }

  def parseFile(inputData: InputData): List[CalorieList] = {
    inputData.multiLineRecordParse(InputData.emptyLineSeparater).map(x=>CalorieList(x.asLineList))
  }

  def sorted(calorieLists: List[CalorieList]) = {
    calorieLists.map(_.total).sorted
  }

  def pt1(inputData: InputData) = {
    sorted(parseFile(inputData)).reverse.head
  }

  def pt2(inputData: InputData):Int = {
    sorted(parseFile(inputData)).reverse.slice(0,3).sum
  }
}
