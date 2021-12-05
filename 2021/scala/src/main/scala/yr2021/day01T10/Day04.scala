package yr2021.day01T10

class Day04 {

  def parseBingoConfig(list: List[String]): (List[Int], List[Int]) = {
    (list.head.split(",").toList.map(_.toInt)
    ,
      list.tail.filter(_.size>0).map(_.split(" +").toList.map(_.toIntOption).flatten).flatten
    )
  }

  def setUpGame(boardData: List[Int]): Map[Int, List[(Int, Int, Int, Int)]] = {
    boardData
      .grouped(25).zipWithIndex
      .map(board=>
        board._1.grouped(5).zipWithIndex.toList
          .map(row=>row._1.zipWithIndex
            .map(column=>
              (board._2, row._2, column._2, column._1))))
      .flatten.flatten.toList
      .groupBy(_._4)
  }

  def playGame(positions: Map[Int, List[(Int, Int, Int, Int)]], boardState: Map[Int, Map[String, List[Int]]], numberList: List[Int]) = {

    val number = numberList.head
    val updates = positions(number)
    val newBoardState = updates.map(x=>x._1->('r'+x._2->x._4 :: boardState(x._1)( 'r'+x._2.toString) ))
    newBoardState

  }

  def pt1(list: List[String]) = {
    val config = parseBingoConfig(list)
    val playerCount = config._2.size / 25
    val positions = setUpGame(config._2)
    val boardState: Map[Int, Map[String, List[Int]]] = Map()
    playGame(positions,boardState, config._1)
  }



}
