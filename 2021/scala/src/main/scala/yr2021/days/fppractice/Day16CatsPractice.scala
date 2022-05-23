package yr2021.days.fppractice

import cats.implicits.catsSyntaxEq
import cats.{Eq, Show}
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._
import cats.instances.long._
import cats.Eq
import cats.syntax.eq._ // for ===

import java.util.Date


class Day16CatsPractice {
  val showInt:    Show[Int]    = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  val intAsString: String =
    showInt.show(123)
  // intAsString: String = "123"

  val stringAsString: String =
    showString.show("abc")
  // stringAsString: String = "abc"

  // Using import cats.syntax.show._
  val shownInt:String = 123.show
  // shownInt: String = "123"

  val shownString:String = "abc".show
  // shownString: String = "abc"

  implicit val dateShow: Show[Date] =
    new Show[Date] {
      def show(date: Date): String =
        s"${date.getTime}ms since the epoch."
    }

  val dateString = new Date().show

  implicit val intFieldShow: Show[IntField] = Show.show[IntField] { intField =>
    val valueString  = intField.value.show
    s"value is $valueString"
  }

  val shownIntField = IntField(67).show

  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val xDate = new Date() // now
  val yDate = {Thread.sleep(123);new Date()}

  val dateCompare1 = xDate === xDate
  val dateCompare2 = xDate === yDate

  implicit val intFieldEqual: Eq[IntField] =
    Eq.instance[IntField] {(intField1, intField2) =>
      intField1.value === intField2.value
    }

  val intFieldCompare1 = IntField(5) === IntField(5)
  val intFieldCompare2 = IntField(5) =!= IntField(5)


}
