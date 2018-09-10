name := "scala101"

version := "0.1"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.12"

lazy val log4jVersion = "11.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.11.0"
libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala" % log4jVersion
libraryDependencies += "joda-time" % "joda-time" % "2.1"
libraryDependencies += "org.joda" % "joda-convert" % "1.3"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "latest.release"
libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "latest.release"