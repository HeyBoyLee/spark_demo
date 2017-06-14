//package com.mi.demo
//
//
//import com.metok.util.Env
//import org.apache.spark.{Logging, SparkConf, SparkContext}
//import org.slf4j.LoggerFactory
////import org.apache.log4j.{Level, LogManager, Logger}
//import org.slf4j.Logger
//
///**
//  * Created by mi on 3/1/17.
//  */
//object HelloSpark {
////  val logger = Logger.getLogger(HelloSpark.getClass)
////  val logger = LogManager.getRootLogger
//  def main(args: Array[String]) {
////    val logger = LogManager.getRootLogger
////    val logger = new MyLogger()
//
////    logger.setLevel(Level.WARN)
//    //val logger = LoggerFactory.getLogger(HelloSpark.getClass)
//    val logger = LoggerFactory.getLogger(""+HelloSpark.getClass)
//    logger.info("######")
//    logger.warn("Usage: WordCountApp --input path --output path")
//
//    args.foreach(logger.info)
//    val sparkConf = new SparkConf().setAppName(s"Spark:${this.getClass.getName}")
//    sparkConf.setIfMissing("spark.master", "local[2]")
//
//    val env = new Env()
//    val sc = new SparkContext(sparkConf)
//    //sc.parallelize(args).saveAsTextFile("/user/h_miui_metok/server/test")
//    logger.info("Hello world")
//    sc.stop()
//  }
//}
//
//class MyLogger extends Logging{
//
//}
//
