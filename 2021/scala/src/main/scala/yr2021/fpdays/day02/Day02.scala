package yr2021.fpdays.day02

import cats.{Functor, Monoid}
import yr2021.common.Coordinate2D
import cats.syntax.semigroup._

/**
 * Introducing monoids
 * Their character is:
 *   trait Monoid[A] {
 *     def op(a1: A, a2: A): A
 *     def zero: A
 *   }
 * Using them to sum two coordinates
 *
 * Example of functor is the Course, the set of directions that the
 */
class Day02 {

  implicit val CoordinateAddMonoid: Monoid[Coordinate2D] =
    new Monoid[Coordinate2D] {
      def combine(a: Coordinate2D, b: Coordinate2D) =  a + b
      def empty = Coordinate2D(0,0)
    }
  def addAll[A](values: List[A])
               (implicit monoid: Monoid[A]): A =
    values.foldRight(monoid.empty)(_ |+| _)

  case class Course[A](coordChanges: List[A])
//  implicit val courseFunctor: Functor[Course] =
//    new Functor[Course] {
//      def map[A, B](value: Course[A])(func: A => B): Course[B] =
//        value.coordChanges.map(func)
//    }

  val directions = Map("forward"-> Coordinate2D(1, 0), "down"->Coordinate2D(0, 1), "up"->Coordinate2D(0,-1))

  def parseLine(line: String) = {
    val strings = line.split(" ")
    directions(strings(0)) * strings(1).toInt
  }



}
