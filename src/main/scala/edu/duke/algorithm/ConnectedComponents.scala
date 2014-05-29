package edu.duke.algorithm

import scala.collection.mutable.ArrayBuffer
// Spark
import org.apache.spark.SparkContext
import org.apache.spark.rdd._
import SparkContext._

object ConnectedComponents {

  def execute(sc: SparkContext, input: String, output:String, iter: Int) {
  
    val file = sc.textFile(input)

    //create the adjacency list of the graph (node id, list of neighbors)
    val graph = file.flatMap{
      s => val nodes = s.split("\\s+")
      Seq((nodes(0).toInt, nodes(1).toInt),(nodes(1).toInt, nodes(0).toInt))
    }.distinct().groupByKey()

    //initialize C_v
    var result: RDD[(Int, Seq[Int])] = graph.map(v => (v._1, (v._2 :+ v._1)))
    result.saveAsTextFile(output+"_initial")

    //map

    for(i <- 1 to iter) {
      val intermediate: RDD[(Int, Seq[Int])] = result.flatMap(v => hash(v._1, v._2))
      result = intermediate.reduceByKey(_.union(_).distinct)
      result.saveAsTextFile(output+"_" + i)
    }

    val finalResult = result.filter(v => export(v._1, v._2))
    finalResult.saveAsTextFile(output+"_final")
  }

  private def hash(vertex: Int, neighbors:Seq[Int]): Seq[(Int, Seq[Int])] = {
    //find the minimum element in C_v
    val min = neighbors.min

    //emit (v_min, C_v)
    val result = new ArrayBuffer[(Int, Seq[Int])]()
    result.append((min, neighbors))

    //emit (u, v_min) for all u in nbrs(V)
    for(u <- neighbors) {
      if(min != u) {
        result.append((u, Seq(min)))
      }
    }
    result
  }

  private def export(vertex: Int, neighbors:Seq[Int]): Boolean = {
    val min = neighbors.min
    if(min == vertex) {
      return true
    }
    else {
      return false
    }
  }

}
