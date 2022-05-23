package yr2021.days
import scala.util.parsing.combinator._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import yr2021.days.Day16V2.WorkflowLexer._
import yr2021.days.Day16V2._

import scala.util.parsing.input.CharSequenceReader

class Day16V2Test extends AnyFlatSpec with RegexParsers {



  behavior of "Day16V2Test"

  it should "Hex2Binary" in {

  }

  it should "parseLine" in {
    assert(Day16V2.parseLine("ab") == "10101011")
  }

  it should "parseType" in {
    assert(Day16V2.WorkflowLexer.apply(packetType, "110") == Right(TYPE(6)))
  }
  it should "parseVersion" in {
    assert(Day16V2.WorkflowLexer.apply(packetVersion, "010") == Right(VERSION(2)))
  }
  it should "numericType" in {
    assert(Day16V2.WorkflowLexer.apply(numericLiteralPacketType, "110") == Left(WorkflowLexerError("string matching regex '100' expected but '1' found")))
    assert(Day16V2.WorkflowLexer.apply(numericLiteralPacketType, "100") == Right(TYPE(4)))
  }
  it should "parseLastLiteral" in {
    assert(Day16V2.WorkflowLexer.apply(lastLiteralBits, "00101") == Right(LAST_LITERAL_BITS("0101")))
  }
  it should "parseLiteral" in {
    assert(Day16V2.WorkflowLexer.apply(literalBits, "10111") == Right(LITERAL_BITS("0111")))
  }
  it should "numericLiteral" in {
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "10111101111011110111101111111000101000") == Right(NUMERIC_LITERAL(125269989)))
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "10111101111011110111111100010100") == Right(NUMERIC_LITERAL(7829477)))
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "101111011110111111100010100") == Right(NUMERIC_LITERAL(489445)))
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "1011110111111100010100") == Right(NUMERIC_LITERAL(30693)))
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "101111111000101000") == Right(NUMERIC_LITERAL(2021)))
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "1011100101") == Right(NUMERIC_LITERAL(117)))
    assert(Day16V2.WorkflowLexer.apply(numericLiteral, "001010") == Right(NUMERIC_LITERAL(5)))
  }
  it should "packetData" in {
    assert(Day16V2.WorkflowLexer.apply(packetData, "1001011110111111100010100") == Right(PACKET_DATA(NUMERIC_LITERAL(30693))))
  }
  it should "packet" in {
    assert(Day16V2.WorkflowLexer.apply(packet, "0111001011110111111100010100") == Right(PACKET(3, NUMERIC_LITERAL(30693))))
  }
  it should "testliteral" in {
    assert(Day16V2.WorkflowLexer("0111001011110111111100010100") == Right(List(PACKET(3, NUMERIC_LITERAL(30693)))))
  }

}
//00111000000000000110111101000101001010010001001000000000