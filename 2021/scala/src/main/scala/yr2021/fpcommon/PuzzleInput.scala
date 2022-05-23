package yr2021.fpcommon

import cats.Functor

import scala.io.Source
import cats.effect._
import cats.instances.function._ // for Functor
import cats.syntax.functor._     // for map

case class PuzzleInput(lines: Seq[String])

object PuzzleInput {

  def inputSource(filename: String): Resource[IO, Source] =
    Resource.make {
      IO.blocking(Source.fromResource(filename))
    } { source =>
      IO.blocking(source.close()).handleErrorWith(_ => IO.unit)
    }

  def readString(origin: Source): IO[String] =
    IO.blocking(origin.mkString)

  def loadFromFile(filename: String): IO[String] = {
    inputSource(filename).use { source =>
      readString(source)
    }
  }

  def asSplit(string: String, splitText: String) = string.split(splitText)
  implicit class PuzzleInputOps(value: String) {
    def asLines(lines: String = value): PuzzleInput =
      PuzzleInput(asSplit(lines, "\n"))
  }
  def loadLinesFromFile(filename: String): IO[PuzzleInput] = {
    for {
      text <- loadFromFile(filename)
    } yield (text.asLines())
  }

  def asIntSeq(list: Seq[String]): Seq[Int] =
    list.map(_.toInt)
}
