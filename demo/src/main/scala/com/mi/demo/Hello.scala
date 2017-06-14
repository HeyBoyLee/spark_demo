/**
  * Created by mi on 3/1/17.
  */
package com.mi.demo

import org.json4s.DefaultFormats
import org.json4s._
import org.json4s.jackson.JsonMethods._

object Hello {

  case class Child(name: String, age: Int, birthdate: Option[java.util.Date])
  case class MetokLogInfo (logLevel: Option[String], logCategory: Option[Int])
  case class MetokApiInfo (serverHost: Option[String], serverGroup: Option[String], serverMode: Option[String], apiName: Option[String],
                           apiVersion: Option[String], restUri: Option[String], method: Option[String])
  case class ClientInfo (clientIp: Option[String], imeiMd5: Option[String], userAgent: Option[String])
  case class ServerAccessLog (time: Option[String], uuid: Option[String], apiInfo: Option[MetokApiInfo], clientInfo: Option[ClientInfo],
                              requestUrl: Option[String], status: Option[Int], responseTime: Option[Double], responseLength: Option[Double],
                              logInfo: Option[String], extra: Option[Map[String, String]], test: Option[Boolean])


  def main(args: Array[String]) {


    implicit val formats = DefaultFormats
    val content="""{"status": "200", "clientInfo": {"userAgent": "Dalvik/2.1.0 (Linux; U; Android 6.0.1; MI 4LTE MIUI/V8.2.1.0.MXDCNDL)",
"clientIp": "110.240.69.58", "imeiMd5": null}, "uuid": "fc02ecc9d0674a8699b0225d970baaa9", "responseLength": "68",
"requestUrl": "GET /api/v2/realip HTTP/1.1", "apiInfo": {"apiName": "realip", "apiVersion": "v2",
"serverHost": "metok.sys.miui.com", "serverMode": "preview", "serverGroup": "geolocate"}, "responseTime": "0",
"time": "2017-03-07 04:55:03.996", "logInfo": {"logLevel": "INFO", "logCategory": "access"}}"""
    println(content)
    val json = parse(content).extract[ServerAccessLog]
    println(json.apiInfo.get.serverGroup.get == "geolocate" )

  }
}

