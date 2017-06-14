package com.mi.demo

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, Locale}

/**
  * Created by mi on 3/13/17.
  */
object TimeTest {

  val time = "2017-03-07 04:58:10"

  def main(args: Array[String]) {
//    args.foreach(println)
//    println(getNowDate())
//    println(parse(time))

    val floatVar = 12.456
    val intVar = 2000
    val stringVar = "菜鸟教程!"
    val fs: String = printf("浮点型变量为 " +
      "%f, 整型变量为 %d, 字符串为 " +
      " %s", floatVar, intVar, stringVar).toString


    val date = printf("%s %s %s", "2017", "03", "19").toString
    println(date.indexOf("("))
//    println(date.substring(0, date.indexOf("()"))
    if(date == "2017 03 19") println("xxxx")
  }

  def getPreHour():String= {
    var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy MM dd HH")
    var cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.HOUR, -1)
    var arr = dateFormat.format(cal.getTime()).split(" ")
    "year="+arr(0)+"/month="+arr(1)+"/day="+arr(2)+"/hour="+arr(3)
  }

  def getNowDate():String={
    var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy MM dd HH")
    var cal: Calendar = Calendar.getInstance()
    cal.set(2017,2,13,1,0,0)
    cal.add(Calendar.HOUR, -1)
    var arr = dateFormat.format(cal.getTime()).split(" ")
    "year="+arr(0)+"/month="+arr(1)+"/day="+arr(2)+"/hour="+arr(3)
  }

  def parse(tm: String): Long ={
    val fm = new SimpleDateFormat("yyyy-MM-dd HH")
    val dt2 = fm.parse(tm)
    dt2.getTime()
  }
}
