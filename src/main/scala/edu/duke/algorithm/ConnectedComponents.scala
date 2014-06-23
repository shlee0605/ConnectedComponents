package edu.duke.algorithm

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

// Spark
import org.apache.spark.rdd._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object ConnectedComponents {

  def execute(sc: SparkContext, input: String, output:String, iter: Int) {
  
    val file = sc.textFile(input)

    //create the adjacency list of the graph (node id, list of neighbors)
    val graph = file.flatMap{
      s => val nodes = s.split("\\s+")
      Seq((nodes(0).toInt, nodes(1).toInt),(nodes(1).toInt, nodes(0).toInt))
    }.distinct().groupByKey()

    // val graph = file.map{
    //   s => val nodes = s.split("\\s+")
    //   (nodes(0).toInt, nodes(1).toInt)
    // }.distinct().groupByKey()

    //initialize C_v
    var result: RDD[(Int, Set[Int])] = graph.map(v => {
      val cv: Set[Int] = v._2.toSet + v._1
      (v._1, cv)
    //}).sortByKey()
    })
    
    result.saveAsTextFile(output+"/initial")
    //run the algorithm
    for(i <- 1 to iter) {
      if(i > 1) {
        result = sc.objectFile(output + "/intermediate/" + (i-1))
      }

      //map phase
      val intermediate: RDD[(Int, Set[Int])] = result.flatMap(v => hash(v._1, v._2))

      //reduce phase
      //result = intermediate.reduceByKey(_.union(_)).sortByKey()
      result = intermediate.reduceByKey(_.union(_))

      //save intermediate result
      result.saveAsTextFile(output+"/round_" + i)

      //serialize the rdd so that we can avoid the long lineage.
      result.saveAsObjectFile(output+"/intermediate/" + i)
    }

    //find the connected components
    val connectedComponents = result.filter(v => export(v._1, v._2))
    connectedComponents.saveAsTextFile(output+"/final")
  }

  private def hash(vertex: Int, neighbors:Set[Int]): Seq[(Int, Set[Int])] = {
    //find the minimum element in C_v
    val min = neighbors.min

    //emit (v_min, C_v)
    val result = new ArrayBuffer[(Int, Set[Int])]()
    result.append((min, neighbors))

    //emit (u, v_min) for all u in nbrs(V)
    for(u <- neighbors) {
      if(min != u) {
        result.append((u, Set(min)))
      }
    }
    result
  }

  //filtering out if v_min of the cluster != v
  private def export(vertex: Int, neighbors:Set[Int]): Boolean = {
    val min = neighbors.min
    if(min == vertex) {
      return true
    }
    else {
      return false
    }
  }
  
}
