package com.metok.util

import java.text.SimpleDateFormat
import java.util.Calendar

/**
  * Created by heyboy on 3/13/17.
  */
object Tool {
  val FORMAT= "yyyy MM dd HH"
  def getPreHourPath(t: String):String= {
    val arr = getFormat(getPreHour()).split(" ")
    val s = t match{
      case "hour" => "year="+arr(0)+"/month="+arr(1)+"/day="+arr(2)+"/hour="+arr(3)
      case "day" => "year="+arr(0)+"/month="+arr(1)+"/day="+arr(2)
    }
    s
  }

  def getFormatPath(str: String, t: String): String={
    try{
      val arr = str.split(" ")
      val s = t match{
        case "hour" => "year="+arr(0)+"/month="+arr(1)+"/day="+arr(2)+"/hour="+arr(3)
        case "day" => "year="+arr(0)+"/month="+arr(1)+"/day="+arr(2)
      }
      s
    }catch{
      case e:Exception => ""
    }
  }

  def parse(tm: String): Long ={
    val fm = new SimpleDateFormat("yyyy-MM-dd HH")
    val dt2 = fm.parse(tm)
    dt2.getTime()
  }

  def getFormat(t: Long): String={
    var dateFormat: SimpleDateFormat = new SimpleDateFormat(Tool.FORMAT)
    dateFormat.format(t)
  }

  def getPreHour():Long= {
    var dateFormat: SimpleDateFormat = new SimpleDateFormat(Tool.FORMAT)
    var cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.HOUR, -1)
    dateFormat.parse(dateFormat.format(cal.getTime())).getTime()
  }

  def isPreHour(time: String):Boolean={
    println(getPreHour())
    if(parse(time) == getPreHour()) true else false
  }
}
