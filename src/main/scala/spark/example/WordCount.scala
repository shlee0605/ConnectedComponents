package spark.example

// Spark
import org.apache.spark.SparkContext
import SparkContext._

object WordCount {

  // Run the word count. Agnostic to Spark's current mode of operation: can be run from tests as well as from main
  def execute(sc: SparkContext, input: String, output:String) {
  
    // Adapted from Word Count example on http://spark-project.org/examples/
    val file = sc.textFile(input)
    val words = file.flatMap(line => tokenize(line))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.saveAsTextFile(output)
  }

  // Split a piece of text into individual words.
  private def tokenize(text : String) : Array[String] = {
    // Lowercase each word and remove punctuation.
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")
  }
}
