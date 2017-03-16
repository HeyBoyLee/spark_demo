package com.metok.util

/**
  * Created by heyboy on 3/13/17.
  */
object Config {
  val HDFS_BASE = "hdfs://c3prc-hadoop/user/h_miui_metok"
  val HDFS_URI = "hdfs://c3prc-hadoop"
  val HDFS_URI_TEST = "hdfs://127.0.0.1:9000/"
  val TAG = ("geolocate", "geofence", "dataReporter", "frequent", "geocode")
  val INPUT_FOLDER = ("metok_server_access_log", "metok_server_business_log")
  var SEPERATE_TASK = "Seperate Log Task"

  def getConf(args: Array[String]): Tuple3[String, String, String] = {
    var input: String = "/user/h_scribe/metok"
    var output: String = "/user/h_miui_metok/server"
    var fsPath: String = ""

    val conf = args.length match {
      case 3 => {
        fsPath = if(args(0)== "test") Config.HDFS_URI_TEST else Config.HDFS_URI
        input = args(1)
        output = args(2)
        (input, output, fsPath)
      }
      case _ => (input, output, Config.HDFS_URI)
    }
    conf
  }
}
