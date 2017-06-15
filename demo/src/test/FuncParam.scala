import spire.math.UInt

/**
  * Created by mi on 6/15/17.
  * => Unit 执行结果为Unit的表达式
  * () => Unit　是一个返回值为Unit的函数
  */
object FuncParam extends App{
  def test1(code: => Unit, default: Int)={
    println("start")
    code
    println("end")
  }
  test1({
    println("1111")
    println("2222")
  },2)


  def test2(code: (Int, Int)=> Int)={
    println("aaaa")
    code(1,3)
    println("bbbb")
  }

  test2{
    println("3333")
    (x,y)=> {
      println(44)
      44
    }
  }

  def func2(x:Int, y:Int):Int ={
    println(x+y)
    x+y
  }

  test2(func2)
}
