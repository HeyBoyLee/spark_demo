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


object BusinessLog {
  case class MetokLogInfo (logLevel: Option[String], logCategory: Option[String])
  case class RespBody (info: Option[String], infocode: Option[String], err: Option[Boolean])
  case class Device (model: Option[String], `type`: Option[String])
  case class App (version: Option[String], name: Option[String])
  case class System (version: Option[String], `type`: Option[String])
  case class Miua (device: Option[Device], app: Option[App], miuaVersion: Option[String], miuaType: Option[String], system:Option[System])
  case class C (ac: Option[String], e: Option[String], mc: Option[String], cc: Option[String], dc: Option[String], s: Option[String], sc: Option[String])
  case class ReportData (vp: Option[String], c: Option[Array[C]], vd: Option[String], t: Option[String])
  case class Param (k: Option[String], miua:Option[Miua], s: Option[String], t: Option[String], report_data: Option[ReportData], v: Option[String],
                    imei: Option[String], netWorkType: Option[String], report_id: Option[String])
  case class ServerRecord (param: Option[Param], respBody: Option[RespBody], respExt: Option[String], reqTimestamp: Option[String],
                           respTimestamp: Option[String])
  case class MetokApiInfo (serverHost: Option[String], serverGroup: Option[String], serverMode: Option[String], apiName: Option[String],
                           apiVersion: Option[String], restUri: Option[String], method: Option[String])

  case class ServerBusinessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], record: Option[ServerRecord], logInfo: Option[MetokLogInfo],
                                extra: Option[Map[String, String]], test: Option[Boolean])

  def main(args: Array[String]) {
    val input = "/user/h_scribe/metok/metok_server_business_log/c3-hadoop-scribe26.bj-metok_server_business_log-2017-03-09_00000"
    val output = "/user/output/geofence"
    val output1 = "/user/output/geolocate"


    val conf = new SparkConf().setAppName("business log Application")
    val sc = new SparkContext(conf)

    val hadoopConf = new org.apache.hadoop.conf.Configuration()
    val hdfs = org.apache.hadoop.fs.FileSystem.get(new URI("hdfs://127.0.0.1:9000/"), hadoopConf)

    val lines = sc.sequenceFile[BytesWritable, BytesWritable](input)
//    var geofenceSet :Set[String] = Set()
    implicit val formats = DefaultFormats
    lines.map(t =>
    {
      implicit val formats = DefaultFormats
      val json = Bytes.toString(t._2.copyBytes())
      val businessLog = read[ServerBusinessLog](json)

      if(businessLog.apiInfo.get.serverGroup.get == "geofence"){
         json+""
      }else ""
    }).filter(t => {
      !t.equals("")
    }).saveAsTextFile(output)

//    val data1 = lines.map(t =>
//    {
//      implicit val formats = DefaultFormats
//      val json = Bytes.toString(t._2.copyBytes())
//      val businessLog = read[ServerBusinessLog](json)
//
//      if(businessLog.apiInfo.get.serverGroup.get == "geolocate"){
//        json+""
//      }
//      else{
//        ""
//      }
//    }).filter(t => {!t.equals("")})
//    println(data.getClass)
//    data.take(10).map(t => {println(t)})
//    data.saveAsTextFile(output)
//    data1.saveAsTextFile(output1)
//    println("######")
//    geofenceSet.take(10).foreach(println)

    sc.stop()
  }
}
