package com.mi.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mi on 6/14/17.
  */
object GroupAndReduce extends  App{
  val conf = new SparkConf().setAppName("GroupAndReduce").setMaster("local")
  val sc = new SparkContext(conf)
//  val words = Array("one", "two", "two", "three", "three", "three")
  val words = Array(("one",1), ("two",2), ("two",2), ("three",3), ("three",3), ("three",3))
  val wordsRDD = sc.parallelize(words).map(word => (word, 1))

  words.distinct.foreach(println)

  val wordsCountWithReduce = wordsRDD.
    reduceByKey((x,y)=> x).
    collect().
    foreach(println)
  val wordsCountWithGroup = wordsRDD.
    groupByKey().
    map(w => (w._1, w._2.sum)).
    collect().
    foreach(println)
}
