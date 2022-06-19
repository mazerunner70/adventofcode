package yr2021.days

import org.scalatest.flatspec.AnyFlatSpec

class Day16V3Test extends AnyFlatSpec {

  behavior of "Day16V3Test"

  it should "parseString" in {
    val binString = "000101010101110110110110"
    assert(Day16V3.parseString(java.lang.Long.parseLong(binString, 2).toHexString.toUpperCase) == binString)
  }

  it should "hex2Bin" in {
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
      for ((k, v) <- hexadecimalMapping)
        assert(Day16V3.hex2Bin(k) == v)
  }

  it should "parsePacketLiteral" in {
    val (p, b) = Day16V3.parsePacket("110100101111111000101000")
    println(p)
    println(b)
    assert(b.length == 3)
    assert(p.isInstanceOf[Day16V3.Literal])
  }

  it should "parsePacketOperation1" in {
    val (p, b) = Day16V3.parsePacket("00111000000000000110111101000101001010010001001000000000")
    println(p)
    println(b)
    assert(b.length == 34)
    assert(p.isInstanceOf[Day16V3.Operation])
    val body = p.asInstanceOf[Day16V3.Operation].children
    assert(body.size == 2)
    assert(body.head.isInstanceOf[Day16V3.Literal])
    val subpacket1 = body.head.asInstanceOf[Day16V3.Literal]
    assert(subpacket1.literal == 10)
  }

  it should "parsePacketOperation2" in {
    val (p, b) = Day16V3.parsePacket("11101110000000001101010000001100100000100011000001100000")
    println(p)
    println(b)
    assert(b.length == 5)
    assert(p.isInstanceOf[Day16V3.Operation])
  }

  it should "sum1" in {
    List(("8A004A801A8002F478", 16), ("620080001611562C8802118E34", 12), ("C0015000016115A2E0802F182340", 23), ("A0016C880162017C3686B18A3D4780", 31)).foreach{
      e => {
        val (p, b) = Day16V3.parsePacket(Day16V3.parseString(e._1))
        println(p)
        val packets = Day16V3.flatten(p)
        println(packets.map(_.packetVersion))
        assert(packets.map(_.packetVersion).sum == e._2)
      }
    }
  }

//  it should "flatten1" in {
//    val input = Day16V3.Operation(4,List(Day16V3.Operation(1,List(Day16V3.Operation(5,List(Day16V3.Literal(6,15)))))))
//
//  }

  it should "pt1" in {
    val day15 = new Day15
    val lines = loadList("days/day16/input.txt")
    val binString = Day16V3.parseString(lines.head)
    val packet = Day16V3.parsePacket(binString)._1
    val packets = Day16V3.flatten(packet)
    assert(packets.map(_.packetVersion).sum == 940)
  }

  it should "pt2" in {
    val day15 = new Day15
    val lines = loadList("days/day16/input.txt")
    val binString = Day16V3.parseString(lines.head)
    val packet = Day16V3.parsePacket(binString)._1
    assert(Day16V3.operate(packet) == 5)
  }
  it should "pt2 tests" in {
    List(("C200B40A82", 3), ("04005AC33890", 54), ("880086C3E88112", 7), ("CE00C43D881120",9)
      , ("D8005AC2A8F0", 1), ("F600BC2D8F", 0), ("9C005AC2F8F0", 0), ("9C0141080250320F1802104A08", 1)).foreach{
      e => {
        val (p, b) = Day16V3.parsePacket(Day16V3.parseString(e._1))
        println(e)
        println(p)
        assert(Day16V3.operate(p) == e._2)
      }
    }
  }

}
