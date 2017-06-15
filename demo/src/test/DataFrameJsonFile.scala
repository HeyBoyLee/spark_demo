import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mi on 6/15/17.
  */
object DataFrameJsonFile extends App{
  val sc = new SparkContext(new SparkConf().setAppName("DataFrameJsonFile").setMaster("local[2]"))
  val sqlContext = new SQLContext(sc)
  val df = sqlContext.jsonFile("/home/mi/downloads/spark/dataframe_jsonfile")
  df.show()
}
