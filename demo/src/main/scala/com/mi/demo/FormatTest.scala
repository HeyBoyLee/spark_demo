package com.mi.demo

/**
  * Created by mi on 4/6/17.
  */
object FormatTest {
  def main(args: Array[String]) {
    val x = (("a", "b"), ("c","d")).productIterator

    for(i <- x){

      println(i.getClass)

    }
  }
}

object TestTuple {
  def main(args: Array[String]) {
    class ttt(a: Any, b: Any, c: Any) extends Tuple3(a, b, c)

    val test = List(new Tuple2(1, 2), new ttt(1, 2, 3), "Hello World")

    for (elem <- test)
      isTuple(elem) match {
        case None => println("Not Tuple")
        case Some(x) => println("Is Tuple" + x)
      }
  }

  val Ptrn = """scala.Tuple(\d+)""".r

  def checka(x: Class[_]): Option[Int] = x match {
    case null => None
    case _ => x.getName match {
      case Ptrn(i) => Some(i.toInt)
      case _ => checka(x.getSuperclass)
    }
  }

  def isTuple(x: AnyRef) = if (x.isInstanceOf[Product]) checka(x.getClass) else None
}

