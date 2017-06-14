/**
  * Created by mi on 3/8/17.
  */

import org.json4s._
import org.json4s.jackson.JsonMethods._

object Json4 {
  case class Child(name: String, age: Int, birthdate: Option[java.util.Date])
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address, children: List[Child])

  def main(args: Array[String]) {


//    val json = parse("""
//         { "name": "joe",
//           "address": {
//             "street": "Bulevard",
//             "city": "Helsinki"
//           },
//           "children": [
//             {
//               "name": "Mary",
//               "age": 5,
//               "birthdate": "2004-09-04T18:06:22Z"
//             },
//             {
//               "name": "Mazy",
//               "age": 3
//             }
//           ]
//         } """)

    implicit val formats = DefaultFormats
    val content="""
          {
             "street": "Bulevard",
             "city": "Helsinki"
           }
           """
    println(content.getClass)
    val json = parse(content)
    println(json.getClass)
    json.extract[Address]

  }
}
