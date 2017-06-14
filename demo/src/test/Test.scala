//import com.metok.task.{FolderFileName, MultiFolderOutput}
import com.metok.util.Struct
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.io.BytesWritable
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization._

/**
  * Created by mi on 6/9/17.
  */

class MultiFolderOutput extends MultipleTextOutputFormat[FolderFileName,String]{
  override def generateFileNameForKeyValue(key:FolderFileName,value:String,name:String):String={
    List(key.folder,key.filename).mkString("/")
  }
  override def generateActualKey(key:FolderFileName,value:String):FolderFileName=null
}

case class FolderFileName(folder:String,filename:String)

case class CellInfo (key: Option[String], mcc: Option[Int], mnc: Option[Int], lac: Option[Int], cid: Option[Int],
                     dbm: Option[Int], connected: Option[Boolean],
                     lng: Option[Double], lat: Option[Double], accuracy: Option[Double])
case class WifiInfo(bssid: Option[String], dbm: Option[Int], connected: Option[Boolean],
                    lng: Option[Double], lat: Option[Double], accuracy: Option[Double])
case class ComputeLog (_id: Option[String], uid: Option[String], sg: Option[String], ts: Option[String],
                       cells: Option[Array[CellInfo]], wifis: Option[Array[WifiInfo]],
                       outlierCells: Option[Array[CellInfo]], outlierWifis: Option[Array[WifiInfo]],
                       lossCells: Option[Array[CellInfo]], lossWifis: Option[Array[WifiInfo]])
case class MetokLogInfo (logLevel: Option[String], logCategory: Option[String])
case class RespBody (info: Option[String], infocode: Option[String], err: Option[Boolean])
case class Device (model: Option[String], `type`: Option[String])
case class App_ (version: Option[String], name: Option[String])
case class System (version: Option[String], `type`: Option[String])
case class Miua (device: Option[Device] = null, app: Option[App] = null, miuaVersion: Option[String] = null, miuaType: Option[String] = null, system:Option[System] = null)
case class C (ac: Option[String], e: Option[String], mc: Option[String], cc: Option[String], dc: Option[String], s: Option[String], sc: Option[String])
case class ReportData (vp: Option[String], c: Option[Array[C]], vd: Option[String], t: Option[String])
case class Spare (center: Option[Array[String]], radius: Option[Int], latitude:Option[Int], longitude: Option[Int], level: Option[String], onlineLoc: Option[Int], offlineLoc: Option[Int])
case class Param (k: Option[String], miua:Option[Miua], s: Option[String], t: Option[String], v: Option[String],
                  imei: Option[String], netWorkType: Option[String],
                  // for dataReporter
                  report_id: Option[String], report_data: Option[ReportData],
                  // for geofence
                  action: Option[String], `type`: Option[String],spare: Option[Spare], current: Option[Array[Double]],
                  // for geolocate
                  consumerApp: Option[Array[String]], cellList: Option[Array[CellInfo]], wifiList: Option[Array[WifiInfo]]
                 )
case class ServerRecordRespExt (computeLog: Option[ComputeLog])
case class ServerRecord (param: Option[Param], respBody: Option[RespBody], respExt: Option[ServerRecordRespExt], reqTimestamp: Option[String],
                         respTimestamp: Option[String])
case class MetokApiInfo (serverHost: Option[String], serverGroup: Option[String], serverMode: Option[String], apiName: Option[String],
                         apiVersion: Option[String], restUri: Option[String], method: Option[String])
case class ServerBusinessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], record: Option[ServerRecord], logInfo: Option[MetokLogInfo],
                              extra: Option[Map[String, String]], test: Option[Boolean])
case class ClientInfo (clientIp: Option[String], imeiMd5: Option[String], userAgent: Option[String], miua: Option[String])
case class ServerAccessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], clientInfo: Option[ClientInfo],
                            requestUrl: Option[String], status: Option[String], responseTime: Option[String], responseLength: Option[String],
                            logInfo: Option[MetokLogInfo], extra: Option[Map[String, String]], test: Option[Boolean], infocode: Option[String])

object Test extends App{
  def splitTime(tm: String): Array[String] ={
    try{
      val arr = tm.split(" ")
      val x = arr(0).split("-")
      val y = arr(1).split(":")
      Array(x(0), x(1), x(2), y(0))
    }catch{
      case ex: Exception => Array()
    }
  }

  private val context: SparkContext = new SparkContext(new SparkConf().setAppName("test").setMaster("local"))
  val lines = context.sequenceFile[BytesWritable, BytesWritable]("/home/mi/input")
  val result = lines.flatMap(t=>{
    implicit val formats = DefaultFormats
    val value = Bytes.toString(t._2.copyBytes())
    val businessLog = read[Struct.ServerBusinessLog](value)
    val tag = businessLog.apiInfo.get.serverGroup.get
    val time = businessLog.time.get
    val Array(year, month, date, hour) = splitTime(time)
    if(hour == "23"&&  tag =="geolocate"){
      Some(FolderFileName(tag, hour), value)
    }else None
  }).saveAsHadoopFile[MultiFolderOutput]("/home/mi/output")

//  context.parallelize(List(
//    (FolderFileName("tag1","hour1"),"a1"),
//    (FolderFileName("tag1","hour2"),"a2"),
//    (FolderFileName("tag2","hour11"),"a3"),
//    (FolderFileName("tag2","hour12"),"a4")
//  ),2).saveAsHadoopFile[MultiFolderOutput]("/home/mi/Test")
}
