/**
  * Created by mi on 3/8/17.
  */

import org.json4s._
import org.json4s.native.JsonMethods._

object JsonNative {
  def main(args: Array[String]) {
    val json = parse("""
         { "name": "joe",
           "children": [
             {
               "name": "Mary",
               "age": 5
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         } """)

    val JObject(i) = json
    val name = json \ "name"
    println(name)

    val arr = for {
      JObject(child) <- json
      JField("name", JString(name)) <-child
      JField("age", JInt(age))  <-child
    } yield (name, age)
//    println(json)
//    println(arr)
  }
}
