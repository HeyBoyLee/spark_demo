package com.mi.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mi on 3/1/17.
  */
object HelloSpark {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName(s"Spark:${this.getClass.getName}")
    sparkConf.setIfMissing("spark.master", "local[2]")

    val sc = new SparkContext(sparkConf)
    println("Hello world")
    sc.stop()
  }
}
