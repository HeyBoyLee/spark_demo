package com.cloud.log

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mi on 6/6/17.
  */

//2017-06-04T19:43:09.723Z 39.190.133.222 [Dalvik/2.1.0 (Linux; U; Android 7.0; Mi-4c MIUI/V8.2.3.0.NXKCNEC)] GET /base/profile/metoknlp/collect/Mi-4c__V8.2.3.0.NXKCNEC__460__0__1.1.20__t_stable 200 137 118.216 ms
object CloudLog extends App {
  val input = "/home/mi/downloads/spark/metok.log.sample" //"input/metok.log.sample"
  val pattern = """.*/metoknlp/collect/(.*[A-Za-z0-9]+)_+(.*[A-Za-z0-9]+)_+(.*[A-Za-z0-9]+)_+(.*[A-Za-z0-9]+)_+(.*[A-Za-z0-9]+)_+(.*)_+(\w+).*200.*""".r
  val sc = new SparkContext(new SparkConf().setAppName("cloud_log").setMaster("local"))

  sc.textFile(input).flatMap(text => {
    try {
      text match {
        case pattern(a, b, c, d, e, f, g) => {
          Some(((a, e, g),1))
        }
        case _ => None
      }
    } catch {
      case ex: Exception => throw ex
    }

  }).distinct().reduceByKey(_+_).foreach(println)

  sc.stop()
}
