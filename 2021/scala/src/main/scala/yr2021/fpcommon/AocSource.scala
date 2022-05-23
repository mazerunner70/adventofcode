package yr2021.fpcommon

import cats.effect.{IO, Resource}

import scala.io.Source

object AocSource {

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

}
