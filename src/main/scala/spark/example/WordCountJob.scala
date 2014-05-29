package spark.example

// Spark
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext
import SparkContext._

object WordCountJob {

  private val master = "local"
  private val input = "hdfs://localhost:9000/test"
  private val output = "hdfs://localhost:9000/output"
  private val jar = "/home/shlee0605/coding/spark-example/target/scala-2.10/spark-example-assembly-0.1.0.jar"
  private val AppName = "WordCountJob"

  def main(args: Array[String]) {
    
    // Run the word count
    val sc = new SparkContext(master, AppName, null, List(jar))
    WordCount.execute(sc, input, output)
    // Exit with success
    System.exit(0)
  }
}
