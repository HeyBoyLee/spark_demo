package com.seperate.task

import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.io.BytesWritable
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._

/**
  * Created by mi on 6/15/17.
  */

case class Record(logCategory:String, serverGroup: String, content: String)
object SeperateLog extends App{
  val sc = new SparkContext(new SparkConf().setAppName("seperate_task").setMaster("local[2]"))
  val lines = sc.sequenceFile[BytesWritable, BytesWritable]("/home/mi/downloads/spark/metok_server_business_log-2017-03-09_00000")
  private val map = lines.flatMap(t => {
    implicit val formats = DefaultFormats
    val content = Bytes.toString(t._2.copyBytes())
    val jValue = parse(content)
    Some(Record((jValue \ "logInfo" \ "logCategory").extract[String], (jValue \ "apiInfo" \ "serverGroup").extract[String], content))
  })
  val results= map

  val sqlContext = new SQLContext(sc)
  val dataFrame = sqlContext.createDataFrame(results).toDF()
  dataFrame.write.partitionBy("logCategory", "serverGroup").text("/home/mi/output/seperate")
}
