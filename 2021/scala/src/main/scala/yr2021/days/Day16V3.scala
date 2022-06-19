package yr2021.days



object Day16V3 {



  val hexadecimalMapping =
    Map(
      '0' -> "0000",
      '1' -> "0001",
      '2' -> "0010",
      '3' -> "0011",
      '4' -> "0100",
      '5' -> "0101",
      '6' -> "0110",
      '7' -> "0111",
      '8' -> "1000",
      '9' -> "1001",
      'A' -> "1010",
      'B' -> "1011",
      'C' -> "1100",
      'D' -> "1101",
      'E' -> "1110",
      'F' -> "1111"
    )

  def pSum(list: List[Long]): Long =
    list.sum

  val mapped = Map[Int, List[Long]=>Long](
    0 -> {x => x.sum},
    1 -> {x => x.product },
    2 -> {x => x.min},
    3 -> {x => x.max},
    4 -> {x => 0},
    5 -> {x => if (x(0) > x(1)) 1 else 0},
    6 -> {x => if (x(0) < x(1)) 1 else 0},
    7 -> {x => if (x(0) == x(1)) 1 else 0},
  )

  sealed abstract class Packet(val packetVersion: Int)
  case class Literal(override val packetVersion: Int, literal: Long) extends Packet(packetVersion)
  case class Operation(override val packetVersion: Int, val packetType: Int, children: List[Packet]) extends Packet(packetVersion)

  def flatten(packet: Packet): List[Packet] = {
    def run(packet: Packet, list: List[Packet]): List[Packet] = packet match {
      case Literal(pv, l) => packet :: list
      case Operation(pv, pt, c) => packet :: c.map(run(_, Nil)).flatten
    }
    run(packet, Nil)
  }
  def operate(packet: Packet): Long = {
    packet match {
      case Literal(pv, l) => l
      case Operation(pv, pt, c) => mapped(pt)(c.map(operate(_)))
    }
  }

  type HexString = String
  type BinString = String
  def hex2Bin(char: Char): BinString =
    hexadecimalMapping(char)
  def parseString(hexString: HexString): BinString =
    hexString.foldRight[List[BinString]](List())((e, acc)=> hex2Bin(e) :: acc).mkString

  def extract(binString: BinString, chunkSize: Int): (BinString, BinString) = binString.splitAt(chunkSize)
  def asInt(binString: BinString): Int = Integer.parseInt(binString, 2)
  def asLong(binString: BinString): Long = java.lang.Long.parseLong(binString, 2)

  def extractUntil(binString: BinString, chunkSize: Int, continuePred: BinString=>Boolean, list: List[BinString]): (List[BinString], BinString) = {
    val (extraction, binString2) = extract(binString, chunkSize)
    if (continuePred(extraction))
      extractUntil(binString2, chunkSize, continuePred, extraction::list)
    else
      ((extraction::list).reverse, binString2)
  }

  def parsePackets(binString: BinString, maxPacketCount: Int, list: List[Packet]): (List[Packet], BinString) = maxPacketCount match {
    case 0 => (list.reverse, binString)
    case n =>
      val (p, bs) = parsePacket(binString)
      parsePackets(bs, maxPacketCount-1, p :: list)
  }

  def parsePackets(binString: BinString, list: List[Packet]): (List[Packet], BinString) = binString.length match {
    case 0 => (list.reverse, binString)
    case n =>
      val (p, bs) = parsePacket(binString)
      parsePackets(bs, p :: list)
  }


  def parsePacket(binString: BinString): (Packet, BinString) = {
    val (packetVersion, binString2) = extract(binString, 3)
    val (packetType, binString3) = extract(binString2, 3)
    asInt(packetType) match {
      case 4 => {
        val (list, bs) = extractUntil(binString3, 5, (bs)=> bs.charAt(0) == '1', List.empty)
        (Literal(asInt(packetVersion), asLong(list.map(x=>x.substring(1)).mkString)), bs)
      }
      case pt => {
        val (operatorType, bs) = extract(binString3, 1)
        operatorType match {
          case "0" => {
            val (bitCount, bs1) = extract(bs, 15)
            val (bits, bs2) = extract(bs1, asInt(bitCount))
            val (packets, bs3) = parsePackets(bits, List.empty)
            (Operation(asInt(packetVersion), pt, packets), bs2)
          }
          case "1" => {
            val (packetCount, bs1) = extract(bs, 11)
            val (list, b) = parsePackets(bs1, asInt(packetCount), List.empty)
            (Operation(asInt(packetVersion), pt, list), b)
          }
        }
      }
    }
  }




}
