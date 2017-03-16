package com.metok.util

/**
  * Created by heyboy on 3/10/17.
  */
object Struct {
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
  case class ServerRecord (param: Option[Param], respBody: Option[RespBody], respext: Option[String], reqTimestamp: Option[String],
                           respTimestamp: Option[String])
  case class MetokApiInfo (serverHost: Option[String], serverGroup: Option[String], serverMode: Option[String], apiName: Option[String],
                           apiVersion: Option[String], restUri: Option[String], method: Option[String])

  case class ServerBusinessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], record: Option[ServerRecord], logInfo: Option[MetokLogInfo],
                                extra: Option[Map[String, String]], test: Option[Boolean])

  case class ClientInfo (clientIp: Option[String], imeiMd5: Option[String], userAgent: Option[String])
  case class ServerAccessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], clientInfo: Option[ClientInfo],
                              requestUrl: Option[String], status: Option[String], responseTime: Option[String], responseLength: Option[String],
                              logInfo: Option[MetokLogInfo], extra: Option[Map[String, String]], test: Option[Boolean])

}
