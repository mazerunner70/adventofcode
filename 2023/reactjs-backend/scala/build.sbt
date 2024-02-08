ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

//lazy val root = (project in file("."))
//  .settings(
//    name := "scala"
//  )
resolvers += "Akka library repository".at("https://repo.akka.io/maven")

// https://mvnrepository.com/artifact/io.spray/spray-json
libraryDependencies += "io.spray" %% "spray-json" % "1.3.6"
//https://sangria-graphql.github.io/learn/#result-marshalling-and-input-unmarshalling
libraryDependencies += "org.sangria-graphql" %% "sangria-spray-json" % "1.0.2"

// https://mvnrepository.com/artifact/org.sangria-graphql/sangria
libraryDependencies += "org.sangria-graphql" %% "sangria" % "4.1.0"

val AkkaVersion = "2.9.1"
val AkkaHttpVersion = "10.6.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
)
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-spray-json
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion


// https://mvnrepository.com/artifact/com.typesafe.slick/slick
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.4.1"
// https://mvnrepository.com/artifact/com.typesafe.slick/slick-hikaricp
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1"

// https://mvnrepository.com/artifact/org.slf4j/slf4j-nop
libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.12"
// https://mvnrepository.com/artifact/com.h2database/h2
libraryDependencies += "com.h2database" % "h2" % "2.2.224"
// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test




