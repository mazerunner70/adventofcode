package yr2021.day01T10

import org.scalatest.flatspec.AnyFlatSpec
import yr2021.common.Util

class Day04Test extends AnyFlatSpec {

  "doing day 4 parse config" should "show config loaded" in {
    val day04 = new Day04()
    val testList = Util.loadList("day01T10/day04/test.txt")
//    list.tail.filter(_.size>0).map(_.split(" *").toList.map(_.toInt)).flatten
    assert(testList.tail.filter(_.size>0).size == 15)
    assert(testList.tail.filter(_.size>0).head == "22 13 17 11  0")
    assert(testList.tail.filter(_.size>0).map(_.split(" +").toList.map(_.toIntOption).flatten).head == List(22, 13, 17, 11, 0))

    val parsedConfig = day04.parseBingoConfig(testList)
    assert (parsedConfig._1 == List(7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1))
  }

  "doing board setup" should "give easy to use board" in {
    val day04 = new Day04()
    val testList = Util.loadList("day01T10/day04/test.txt")
    val parsedConfig = day04.parseBingoConfig(testList)
    assert( parsedConfig._2.grouped(25).zipWithIndex.toList.head == (List(22, 13, 17, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19),0))
    assert( parsedConfig._2.grouped(25).zipWithIndex.map(x=>(x._2, x._1.grouped(5).zipWithIndex.toList)).toList.head == (0,List((List(22, 13, 17, 11, 0),0), (List(8, 2, 23, 4, 24),1), (List(21, 9, 14, 16, 7),2), (List(6, 10, 3, 18, 5),3), (List(1, 12, 20, 15, 19),4))))
//    assert(parsedConfig._2.grouped(25).zipWithIndex.map(board=>(board._2, board._1.grouped(5).zipWithIndex.toList.map(row=>(row._2, row._1.zipWithIndex.map(column=>(board._2, row._2, column._2, column._1)))))).toList.head == List())
    assert(parsedConfig._2.grouped(25).zipWithIndex.map(board=>board._1.grouped(5).zipWithIndex.toList.map(row=>row._1.zipWithIndex.map(column=>(board._2, row._2, column._2, column._1)))).toList.head.head == List((0,0,0,22), (0,0,1,13), (0,0,2,17), (0,0,3,11), (0,0,4,0)))
    assert(List((0,0,0,22), (0,0,1,13), (0,0,2,17), (0,0,3,22), (0,0,4,13)).groupBy(_._4) == Map(13 -> List((0,0,1,13), (0,0,4,13)), 17 -> List((0,0,2,17)), 22 -> List((0,0,0,22), (0,0,3,22))))
  }

  "doing pt1" should "give th eanswer" in {
    val day04 = new Day04()
    val testList = Util.loadList("day01T10/day04/test.txt")
    val parsedConfig = day04.parseBingoConfig(testList)
    val positions = day04.setUpGame(parsedConfig._2)
    assert(day04.playGame(positions, Map(), parsedConfig._1) == Map())

  }

}
