name := "scala"

version := "0.1"

scalaVersion := "2.13.7"


libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.4"
// https://mvnrepository.com/artifact/org.scalactic/scalactic
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test

// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-parser-combinators
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"

