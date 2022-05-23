package yr2021.fpdays.day02

import cats.{Functor, Monoid}
import yr2021.common.Coordinate
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

  implicit val CoordinateAddMonoid: Monoid[Coordinate] =
    new Monoid[Coordinate] {
      def combine(a: Coordinate, b: Coordinate) =  a + b
      def empty = Coordinate(0,0)
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

  val directions = Map("forward"-> Coordinate(1, 0), "down"->Coordinate(0, 1), "up"->Coordinate(0,-1))

  def parseLine(line: String) = {
    val strings = line.split(" ")
    directions(strings(0)) * strings(1).toInt
  }



}
