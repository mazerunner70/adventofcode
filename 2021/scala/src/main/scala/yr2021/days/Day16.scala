package yr2021.days

case class PacketHeader(versionid: Int, typeId: Int)
sealed abstract class Packet{def packetHeader: PacketHeader}
case class NumericLiteral(packetHeader: PacketHeader, number: Long) extends Packet
object Day16 {
  def unit[A](a: A): Extract[A] =
    rng=> (a, rng)
  //https://github.com/fpinscala/fpinscala/blob/second-edition/answerkey/state/06.answer.md
  def map2[A,B,C](ra: Extract[A], rb: Extract[B])(f: (A, B) => C): Extract[C] =
    rng => {
      val (a, r1) = ra(rng)
      val (b, r2) = rb(r1)
      (f(a, b), r2)
    }
  def sequence[A](fs: List[Extract[A]]): Extract[List[A]] =
    fs.foldRight(unit(List[A]()))((f, acc) => map2(f, acc)(_ :: _))


  type BinSource = List[Char]
  def Hex2Binary(c: Char): String =
    Integer.parseInt(c.toString, 16).toBinaryString
  def parseLine(line: String): BinSource = {
    line.foldLeft[List[String]](List.empty)((acc, c)=> Hex2Binary(c) :: acc)
      .reverse.mkString.toList
  }
  def nextChar(binSource: BinSource): (Char, BinSource) =
    (binSource.head, binSource.tail)
  type Extract[+A] = BinSource => (A, BinSource)
  val char: Extract[Char] = nextChar
  def _chars(count: Int): Extract[List[Char]] =
    sequence(List.fill(count)(char))

  def takeWhile(count: Int, f: List[Char]=>Boolean, binSource: BinSource): (List[List[Char]], BinSource) = {
    def go(acc: List[List[Char]], newSource: BinSource): (List[List[Char]], BinSource) = {
      val (charList, newBinSource) = _chars(count)(newSource)
      if (f(charList)) go(charList::acc, newBinSource) else ((charList::acc).reverse, newBinSource)
    }
    go(List(), binSource)
  }
  def asString(charList: List[Char]): String =
    charList.mkString

  def asNumeric(charList: List[Char]): Int = {
    Integer.parseInt(asString(charList), 2)
  }
  def parsePacketHeader(binSource: BinSource): ( PacketHeader, BinSource) = {
    val (charLists, newBinSource) = sequence(List(_chars(3), _chars(3)))(binSource)
    (PacketHeader(asNumeric(charLists(0)), asNumeric(charLists(1))), newBinSource)
  }
  def parseNumericLiteral(binSource: BinSource): (Long, BinSource) = {
    val (charLists, newBinSource) = takeWhile(5, (x=>x(0) == '1'), binSource)
    val number = java.lang.Long.parseLong(charLists.map(_.tail).mkString, 2)
    (number, newBinSource)
  }
  def parseNumeric(bitCount: Int, binSource: BinSource): (Int, BinSource) = {
    val (charList, newBinSource) = _chars(bitCount)(binSource)
    (asNumeric(charList), newBinSource)
  }

//  def ParsePacket(binSource: BinSource) = {
//    val result = parsePacketHeader(binSource) match {
//      case (PacketHeader(versionid, 4), newSource) => {
//        val (number, newSource) = parseNumericLiteral(newSource)
//        (NumericLiteral(PacketHeader(versionid, 4), number), newSource)
//      }
//
//    }
//
//  }


}
