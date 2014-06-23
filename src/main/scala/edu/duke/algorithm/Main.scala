package edu.duke.algorithm

// Spark
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

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
    val appName = "ConnectedComponents"

    val conf = new SparkConf().setAppName(appName).setMaster(master)
    //conf.set("spark.eventLog.enabled", "true")
    //conf.set("spark.eventLog.dir", "file:///home/shlee0605/snap/connected/event")
    conf.setJars(Seq("target/scala-2.10/connected-components-assembly-0.1.0.jar"))

    // Run the word count
    val sc = new SparkContext(conf)
    ConnectedComponents.execute(sc, input, output, iteration)
    // Exit with success
    System.exit(0)
  }
}
