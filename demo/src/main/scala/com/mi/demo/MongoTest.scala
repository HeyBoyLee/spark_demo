package com.mi.demo

import com.mongodb.casbah.Imports._

/**
  * Created by mi on 3/17/17.
  */
object MongoTest {
  def main(args: Array[String]) {
    val uri = MongoClientURI("mongodb://127.0.0.1:27017/")
    val client = MongoClient(uri)
    val collection = client("test")("people")
    val query = MongoDBObject("name"-> "LEE")
    val update = $set("type"->"ok")
    val update1 = $inc("lng" -> 1)
    println(collection.findOne(query))
    collection.update(query, update ++ update1)
  }
}
