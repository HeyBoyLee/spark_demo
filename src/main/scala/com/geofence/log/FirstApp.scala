package com.geofence.log

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
/**
  * Created by mi on 3/7/17.
  */


object FirstApp {
  case class MetokLogInfo (logLevel: Option[String], logCategory: Option[String])
  case class MetokApiInfo (serverHost: Option[String], serverGroup: Option[String], serverMode: Option[String], apiName: Option[String],
                           apiVersion: Option[String], restUri: Option[String], method: Option[String])
  case class ClientInfo (clientIp: Option[String], imeiMd5: Option[String], userAgent: Option[String])
  case class ServerAccessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], clientInfo: Option[ClientInfo],
                           requestUrl: Option[String], status: Option[String], responseTime: Option[String], responseLength: Option[String],
                           logInfo: Option[MetokLogInfo], extra: Option[Map[String, String]], test: Option[Boolean])

  def main(args: Array[String]) {
    val file = "/user/h_scribe/metok/metok_server_access_log/c3-hadoop-scribe25.bj-metok_server_access_log-2017-03-07_00001"
    val output = "/user/output/firstapp_1"

    val conf = new SparkConf().setAppName("First Application")
    val sc = new SparkContext(conf)

    val hadoopConf = new org.apache.hadoop.conf.Configuration()
    val hdfs = org.apache.hadoop.fs.FileSystem.get(new URI("hdfs://127.0.0.1:9000/"), hadoopConf)


    // hdfs.delete(output, true)

    val lines = sc.sequenceFile[BytesWritable, BytesWritable](file)

    // val data = lines.map(t => ThriftUtil.convertBytesToThriftObjectCompact(t._2.copyBytes(), new ServerAccessLog())).flatMap(t => {val s = t.time})
    implicit val formats = DefaultFormats
    val data = lines.map(t =>
      {
        implicit val formats = DefaultFormats
        val json = Bytes.toString(t._2.copyBytes())
        read[ServerAccessLog](json)
      })
    val data2 = lines.map(t =>
    {
      implicit val formats = DefaultFormats
      Bytes.toString(t._2.copyBytes())
    })

//  data.saveAsTextFile(output)
    data.take(2).map(t => {println(t.status.get)})

    sc.stop()
  }
}
