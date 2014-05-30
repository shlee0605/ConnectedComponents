package edu.duke.algorithm

// Spark
import org.apache.spark.SparkContext
import SparkContext._

object Main {
  def main(args: Array[String]) {

    if (args.length < 4) {
      System.err.println("Usage: ConnectedComponents <master> <iteration> <input> <output>")
      System.exit(1)
    }

    val master = args(0)
    val iteration = args(1).toInt
    val input = args(2)
    val output = args(3)
    val AppName = "ConnectedComponents"
    val jar = "target/scala-2.10/connected-components-assembly-0.1.0.jar"

    // Run the word count
    val sc = new SparkContext(master, AppName, null, List(jar))
    ConnectedComponents.execute(sc, input, output, iteration)
    // Exit with success
    System.exit(0)
  }
}
