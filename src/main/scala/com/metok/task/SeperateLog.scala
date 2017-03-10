package com.metok.task

import com.xiaomi.miliao.utils.ThriftUtil
import org.apache.hadoop.io.BytesWritable
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.hadoop.hbase.util.Bytes
import java.net.URI

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import com.metok.util.BaseStruct
import org.apache.hadoop.fs.FileSystem
/**
  * Created by heyboy on 3/10/17.
  */
object SeperateLog {

  def main(args: Array[String]) {
    var hdfs: FileSystem = null
    var input : String = ""
    var output : String = ""

    val hadoopConf = new org.apache.hadoop.conf.Configuration()
    if(args(0) == "test"){
      hdfs = org.apache.hadoop.fs.FileSystem.get(new URI(BaseStruct.HDFS_URI_TEST), hadoopConf)
      input = "/user/h_scribe/metok/metok_server_business_log/c3-hadoop-scribe26.bj-metok_server_business_log-2017-03-09_00000"
      output = "/user/output/geofence"
    }else{

    }

    val conf = new SparkConf().setAppName("business log Application")
    val sc = new SparkContext(conf)

    val lines = sc.sequenceFile[BytesWritable, BytesWritable](input)

    implicit val formats = DefaultFormats
    lines.map(t =>
    {
      implicit val formats = DefaultFormats
      val json = Bytes.toString(t._2.copyBytes())
      val businessLog = read[BaseStruct.ServerBusinessLog](json)

      if(businessLog.apiInfo.get.serverGroup.get == "geofence"){
        json+""
      }else ""
    }).filter(t => {
      !t.equals("")
    })
      .take(2).foreach(println)
    //.saveAsTextFile(output)
    sc.stop()
  }
}
