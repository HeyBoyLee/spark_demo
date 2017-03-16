package com.metok.util

/**
  * Created by heyboy on 3/13/17.
  */
import java.net.URI

import org.apache.hadoop.fs.FileSystem
import org.apache.spark.{SparkConf, SparkContext}

class Env {
  def initHadoop(path: String): FileSystem ={
    val hadoopConf = new org.apache.hadoop.conf.Configuration()
    FileSystem.get(new URI(path), hadoopConf)
  }

  def initSpark(appName: String):SparkContext ={
    val conf = new SparkConf().setAppName(appName)
    new SparkContext(conf)
  }
}


