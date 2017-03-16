package com.metok.task

import com.xiaomi.miliao.utils.ThriftUtil
import org.apache.hadoop.io.BytesWritable
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.hadoop.hbase.util.Bytes
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization._
import com.metok.util._
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.plans.logical.MapPartitions
import org.apache.hadoop.fs.Path
/**
  * Created by heyboy on 3/10/17.
  */
object SeperateLog {

  def main(args: Array[String]) {

    args.foreach(println)

    var (input , output, fsPath) = Config.getConf(args)
    val env = new Env()
    val hdfs =  env.initHadoop(fsPath)
    val sc = env.initSpark(Config.SEPERATE_TASK)


    Config.INPUT_FOLDER.productIterator.foreach(folder => {
      val iu = input+"/"+folder+"/"+Tool.getPreHourPath("day")
      val lines = sc.sequenceFile[BytesWritable, BytesWritable](iu)
      Config.TAG.productIterator.foreach(tag => {
        val ou = output+"/"+folder+"/"+tag+"/"+Tool.getPreHourPath("hour")
        if(!hdfs.exists(new Path(ou))){
          lines.map(t =>
          {
            try{
              implicit val formats = DefaultFormats
              val json = Bytes.toString(t._2.copyBytes())
              val businessLog = read[Struct.ServerBusinessLog](json)

              if(businessLog.apiInfo.get.serverGroup.get == tag && Tool.isPreHour(businessLog.time.get)){
                json+""
              }else ""
            }catch{
              case ex: Exception => ""
            }
          }).filter(t => {
            !t.equals("")
          }).distinct()//.saveAsTextFile(ou)
        }
      })
    })

    sc.stop()
  }
}
