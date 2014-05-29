import AssemblyKeys._

assemblySettings

excludedJars in assembly <<= (fullClasspath in assembly)

name := "spark-example"

version := "0.1.0"

scalaVersion := "2.10.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "0.9.1"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.2.3"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "1.2.1"
