package edu.duke.algorithm

// Spark
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext
import SparkContext._

object Main {

  private val master = "local"
  private val input = "input/kronecker_graph_10.txt"
  private val output = "output"
  private val iter = 5
  private val jar = "target/scala-2.10/spark-example-assembly-0.1.0.jar"
  private val AppName = "ConnectedComponents"

  def main(args: Array[String]) {

    // Run the word count
    val sc = new SparkContext(master, AppName, null, List(jar))
    ConnectedComponents.execute(sc, input, output, iter)
    // Exit with success
    System.exit(0)
  }
}
