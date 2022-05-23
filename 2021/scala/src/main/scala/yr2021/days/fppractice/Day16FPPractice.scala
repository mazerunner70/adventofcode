package yr2021.days.fppractice

// Following this book: https://www.scalawithcats.com/dist/scala-with-cats.html


  trait Field
  final case class IntField(value: Int) extends Field

  final case class Header(version: IntField, typeId: IntField)

  trait Packet
  final case class Literal(header: Header, value: Int) extends Packet
  final case class Operator(header: Header, get: List[Packet]) extends Packet

  trait FieldWriter[A] {
    def write(value: A): Field
  }

  trait PacketWriter[A] {
    def write(value: A): Packet
  }

  object FieldWriterInstances {
    implicit val IntFieldWriter: FieldWriter[Int] =
      new FieldWriter[Int] {
        def write(value: Int): Field =
          IntField(value)
      }
  }

  trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val stringPrintable = new Printable[String] {
      def format(input: String) = input
    }

    implicit val intPrintable = new Printable[Int] {
      def format(input: Int) = input.toString
    }
  }

  object Printable {
    def format[A](input: A)(implicit p: Printable[A]): String =
      p.format(input)
    def print[A](input: A)(implicit p: Printable[A]): Unit =
      println(format(input))
  }

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      p.format(value)
    def print(implicit p: Printable[A]): Unit =
      println(p.format(value))
  }
}

class Day16FPPractice {
  import PrintableInstances._
  import PrintableSyntax._
  implicit val intFieldPrintable = new Printable[IntField] {
    def format(intField: IntField) = {
      val value = Printable.format(intField.value)
      s"Value is $value"
    }
  }

  def printIntField1(value: Int) =
    Printable.format(IntField(value))
  def printIntField2(value: Int) =
    IntField(value).format

//  def readDigits(binaryString: String, count: Int): (String, String) = binaryString.splitAt(count)
//
//  def readPacket(binaryString: String): (Packet, String) = {
//    val (packetVersion, remainder) = readDigits(binaryString)
//  }
//
//  def parseLine(line: String) = {
//    val binaryString: String = line.foldLeft("")((a, e)=> a + e.toBinaryString)
//
//  }

}
