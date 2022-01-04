package yr2021.days

class Day04 {

  def parseBingoConfig(list: List[String]): (List[Int], List[Int]) = {
    (list.head.split(",").toList.map(_.toInt)
    ,
      list.tail.filter(_.size>0).map(_.split(" +").toList.map(_.toIntOption).flatten).flatten
    )
  }

  def setUpGameOptimised(boardData: List[Int]): Map[Int, List[(Int, Int, Int, Int)]] = {
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

  def playGameOptimised(positions: Map[Int, List[(Int, Int, Int, Int)]], gameState: Map[Int, List[Int]], numberList: List[Int]): (Int, Int) = {

    val number = numberList.head
    val updates = positions(number)
    val newGameState = updates.foldLeft(gameState)((acc, t)=> {
      val gameStateIdRow = t._1*10
      acc + (gameStateIdRow+t._2-> (t._4 :: acc.getOrElse(gameStateIdRow+t._2, List())),
       gameStateIdRow+5+t._3-> (t._4 :: acc.getOrElse(gameStateIdRow+5+t._3, List())))
    })
    newGameState.filter(_._2.size == 5) match {
      case state if state.size ==1  => (number, state.head._1/10)
      case _ => playGameOptimised(positions, newGameState, numberList.tail)
    }

  }

  def pt1Optimised(list: List[String]) = {
    val config = parseBingoConfig(list)
    val playerCount = config._2.size / 25
    val positions = setUpGameOptimised(config._2)
    val (number, winningBoard) = playGameOptimised(positions,Map(), config._1)
    val calledNumbers = number :: config._1.takeWhile(_ != number)
    val board = config._2.grouped(25).toList(winningBoard)
    val uncalledNumbers = board diff calledNumbers
    uncalledNumbers.sum * number
  }

  def playGameSimple(boards: List[List[List[Int]]], numbers: List[Int]): (Int, Int) = {
    val number = numbers.head
    val newBoards = boards.map(rows=> rows.map(cells=> cells.map(cell=>if (cell == number) -1 else cell)))
    val boardRows = newBoards.zipWithIndex.map(board=>(board._1, board._2))
    def winningRows(boardRows: List[(List[List[Int]], Int)]): List[(List[Int], Int)] = boardRows.map(board=>(board._1.map(row=>row.sum).filter(_== -5), board._2)).
        filter(board=>board._1.size>0)
    val winners: List[(List[Int], Int)] = winningRows(boardRows.map(x=>(x._1.transpose,x._2))) ++ winningRows(boardRows)
    if (winners.size >0)
      (winners.last._2, number * newBoards(winners.last._2).flatten.map(x=>if (x== -1) 0 else x).sum)
    else
      playGameSimple(newBoards, numbers.tail)
   }

  def pt1Simple(list: List[String]) = {
    val config = parseBingoConfig(list)
    val boards = config._2.grouped(25).map(_.grouped(5).toList).toList
    playGameSimple(boards, config._1)._2
  }

  def playPt2GameSimpleToEnd(boards: List[List[List[Int]]], numbers: List[Int]):Int = {
    val (winningBoard, score) = playGameSimple(boards,numbers)
    if (boards.size == 1)
      score
    else
      playPt2GameSimpleToEnd(boards.take(winningBoard) ++ boards.drop(winningBoard + 1), numbers)
  }

  def pt2Simple(list: List[String]) = {
    val config = parseBingoConfig(list)
    val boards = config._2.grouped(25).map(_.grouped(5).toList).toList
    playPt2GameSimpleToEnd(boards, config._1)
  }


}
