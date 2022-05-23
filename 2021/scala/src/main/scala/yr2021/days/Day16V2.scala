package yr2021.days

import scala.util.parsing.combinator._
import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{Position, Reader}
object Day16V2 {


  type BinString = String

  type BinSource = List[Char]
  def hex2Binary(c: Char): String =
    Integer.parseInt(c.toString, 16).toBinaryString
  def parseLine(line: String): BinString = {
    line.foldLeft[List[String]](List.empty)((acc, c) => hex2Binary(c) :: acc)
      .reverse.mkString
  }

  object WorkflowLexer extends RegexParsers {

  trait WorkflowToken
  trait PacketData extends WorkflowToken
  trait Packet extends WorkflowToken

  case class VERSION(packetVersion: Int) extends WorkflowToken
  case class TYPE(packetType: Int) extends WorkflowToken
  case class NUMERIC_LITERAL_TYPE(packetType: Int) extends WorkflowToken
  case class LITERAL_BITS(bits: String) extends WorkflowToken
  case class LAST_LITERAL_BITS(bits: String) extends WorkflowToken
  case class NUMERIC_LITERAL(number: Long) extends PacketData
  case class OPERATOR(packets: Parser[List[PACKET]]) extends PacketData
  case class LENGTH_TYPE(lengthType: Int) extends WorkflowToken
  case class DEFINED_LENGTH(length: Int) extends WorkflowToken
  case class SUB_PACKET_COUNT(count: Int) extends WorkflowToken
  case class PACKET_DATA(packetData: PacketData) extends WorkflowToken
  case class PACKET(packetVersion: Int, packetData: PacketData) extends Packet

  trait WorkflowCompilationError
  case class WorkflowLexerError(msg: String) extends WorkflowCompilationError

    object Util {
      private case class LengthedInput(in: Input, left: Int) extends Input {
        override def first: Elem = in.first

        override def rest: Reader[Elem] = {
          if (atEnd)
            this // required by rest scaladoc
          else
            LengthedInput(in.rest, left - 1)
        }

        override def pos: Position = in.pos

        override def atEnd: Boolean = left <= 0 || in.atEnd
      }
      def lengthed[A](length: Int, p: => Parser[A]): Parser[A] = (in: Input) => {
        p(LengthedInput(in, length)) match {
          case Success(result, next) => Success(result, in.drop(length))
          case Failure(msg, next) => Failure(msg, in)
          case Error(msg, next) => Error(msg, in)
        }
      }
    }
    def packetVersion: Parser[VERSION] = {
      "[01]{3}".r ^^ { str => VERSION(Integer.parseInt(str, 2)) }
    }

    def packetType: Parser[TYPE] = {
      "[01]{3}".r ^^ { str => TYPE(Integer.parseInt(str, 2)) }
    }

    def numericLiteralPacketType: Parser[TYPE] = {
      "100".r ^^ { str => TYPE(Integer.parseInt(str, 2)) }
    }
    def operatorPacketType: Parser[TYPE] = {
      ("000".r | "001".r | "010".r | "011".r | "101".r | "110".r | "111".r) ^^ { str => TYPE(Integer.parseInt(str, 2)) }
    }

    def literalBitsGrouped(repeats:Int): Parser[LITERAL_BITS] = {
      repN(repeats, literalBits) ^^ {
        case list => LITERAL_BITS(list.foldLeft("")((acc, e) => acc + e.bits))
      }
    }

    def literalBits: Parser[LITERAL_BITS] = {
      "1[01]{4}".r ^^ { str => LITERAL_BITS(str.substring(1, 5)) }
    }

    def lastLiteralBits: Parser[LAST_LITERAL_BITS] = {
      "0[01]{4}".r ^^ { str => LAST_LITERAL_BITS(str.substring(1, 5)) }
    }

    def literalBits0: Parser[LITERAL_BITS] = {
      lastLiteralBits ~ "0" ^^ {
        case llb ~ dum => LITERAL_BITS(llb.bits)
      }
    }
    def literalBits1: Parser[LITERAL_BITS] = {
      literalBitsGrouped(1) ~ lastLiteralBits  ^^ {
        case lb ~ llb => LITERAL_BITS(lb.bits + llb.bits)
      }
    }
    def literalBits2: Parser[LITERAL_BITS] = {
      literalBitsGrouped(2) ~ lastLiteralBits ~ "000" ^^ {
        case lb ~ llb ~ dum => LITERAL_BITS(lb.bits + llb.bits)
      }
    }
    def literalBits3: Parser[LITERAL_BITS] = {
      literalBitsGrouped(3) ~ lastLiteralBits ~ "00" ^^ {
        case lb ~ llb ~ dum => LITERAL_BITS(lb.bits + llb.bits)
      }
    }
    def literalBitsN: Parser[LITERAL_BITS] = {
      opt(rep(literalBitsGrouped(4)))  ^^ {
        case Some(list) => LITERAL_BITS(list.foldLeft("")((acc, e) => acc + e.bits))
        case None       => LITERAL_BITS("")
      }
    }

    def numericLiteral: Parser[NUMERIC_LITERAL] = {
      literalBitsN ~ (literalBits0 | literalBits1 | literalBits2 | literalBits3) ^^ {
        case lbn ~ lb => NUMERIC_LITERAL(java.lang.Long.parseLong(  lbn.bits + lb.bits, 2))
      }
    }

    def lengthType: Parser[LENGTH_TYPE] = {
      "[01]".r ^^ { str => LENGTH_TYPE(Integer.parseInt(str, 2)) }
    }
    def definedLengthType: Parser[LENGTH_TYPE] = {
      "0".r ^^ { str => LENGTH_TYPE(Integer.parseInt(str, 2)) }
    }
    def packetCountLengthType: Parser[LENGTH_TYPE] = {
      "1".r ^^ { str => LENGTH_TYPE(Integer.parseInt(str, 2)) }
    }
    def definedLength: Parser[DEFINED_LENGTH] = {
      "[01]{15}".r ^^ { str => DEFINED_LENGTH(Integer.parseInt(str, 2)) }
    }
    def packetCount: Parser[SUB_PACKET_COUNT] = {
      "[01]{11}".r ^^ { str => SUB_PACKET_COUNT(Integer.parseInt(str, 2)) }
    }
    def operator: Parser[OPERATOR] = {
      definedLengthType ~ definedLength ^^ {
        case dlt ~ dl => OPERATOR( Util.lengthed(dl.length, rep(packet)))
      }
      packetCountLengthType ~ packetCount ^^ {
        case pclet ~ pc => OPERATOR(repN(pc.count, packet))
      }
    }

    def packetData: Parser[PACKET_DATA] = {
      operatorPacketType ~ operator ^^ {
        case pt ~ op => PACKET_DATA(op)
      }
      numericLiteralPacketType ~ numericLiteral ^^ {
        case pt ~ nl => PACKET_DATA(nl)
      }
   }

    def packet: Parser[PACKET] = {
      packetVersion ~ packetData ^^ {
        case pv ~ pd => PACKET(pv.packetVersion, pd.packetData)
      }
    }

    def tokens: Parser[List[WorkflowToken]] = {

      phrase(rep1(packet)) ^^ { rawTokens =>
        rawTokens
      }
    }

    def apply(code: String): Either[WorkflowLexerError, List[WorkflowToken]] = {
      applyList(tokens, code)
    }

    def apply[T](parser: Parser[T], code: String): Either[WorkflowLexerError, T] = {
      parse(parser, code) match {
        case NoSuccess(msg, next) => Left(WorkflowLexerError(msg))
        case Success(result, next) => Right(result)
      }
    }

    def applyList[T](parser: Parser[List[T]], code: String): Either[WorkflowLexerError, List[T]] = {
      parse(parser, code) match {
        case NoSuccess(msg, next) => Left(WorkflowLexerError(msg))
        case Success(result, next) => Right(result)
      }
    }
  }

}


// https://github.com/sim642/adventofcode/blob/master/src/main/scala/eu/sim642/adventofcode2021/Day16.scala