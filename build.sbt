import AssemblyKeys._

assemblySettings

excludedJars in assembly <<= (fullClasspath in assembly)

name := "connected-components"

version := "0.1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.0.0"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.2.3"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "1.2.1"
