package yr2021.day01T10

class Day02 {

  val directions = Map("forward"-> (1, 0), "down"->(0, 1), "up"->(0,-1))

  def tupleMultiply(tuple: (Int, Int), magnitude: Int): (Int, Int) = {
    (tuple._1 * magnitude, tuple._2 * magnitude)
  }

  def tupleAdd(tupleA: (Int, Int), tupleB: (Int, Int)): (Int, Int) =
    (tupleA._1+tupleB._1, tupleA._2+tupleB._2)

  implicit class TupleProduct(val p: Product) {
    def product = {
      p.productIterator.collect {
        case x: java.lang.Number => x.intValue()
      }.product
    }
  }

  def prepareList(list: List[String]): List[(Int, Int)] = {
    list.map(_.split(" ")).                      //Instructions as text
      map(cmd=> (directions(cmd(0)), cmd(1).toInt) ).
      map(x=>tupleMultiply(x._1, x._2))                 //Instructions as vectors
  }

  def pt1(list: List[String]): Int = {
    prepareList(list).                                  //Instructions as vectors
      foldLeft((0,0)) ((a, d)=>tupleAdd(a, d)).         //Final position
      product
  }

  def calcWithAim(list: List[(Int, Int)]) = {
    def iter(listLeft:List[(Int, Int)], hor: Int, depth: Int, aim: Int): Int = listLeft match {
      case Nil => hor * depth
      case h :: t => iter(t, hor + h._1, depth +h._1 * aim, aim + h._2)
    }
    iter(list, 0, 0, 0)
  }

  def pt2(list: List[String]): Int = {
    calcWithAim(prepareList(list))                                  //Instructions as vectors
  }

}
