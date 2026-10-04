import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try
import scala.math._

object Abhay_8_ReadCSVStats {

  def main(args: Array[String]): Unit = {

    // Open CSV file
    val reader = CSVReader.open(new File("students.csv"))
    val allRows = reader.allWithHeaders()
    reader.close()

    // Find numeric columns
    val numericCols = allRows.head.keys.filter { col =>
      Try(allRows.head(col).toDouble).isSuccess
    }.toList

    println(s"Numeric Columns: ${numericCols.mkString(", ")}\n")

    // Calculate statistics for each numeric column
    for (col <- numericCols) {

      val values = allRows.flatMap(row =>
        Try(row(col).toDouble).toOption
      )

      val count = values.size
      val mean = values.sum / count
      val minVal = values.min
      val maxVal = values.max
      val stdDev = sqrt(values.map(x => pow(x - mean, 2)).sum / count)

      println(s"Column: $col")
      println(s"Count  : $count")
      println(f"Mean   : $mean%.2f")
      println(f"Min    : $minVal%.2f")
      println(f"Max    : $maxVal%.2f")
      println(f"Std Dev: $stdDev%.2f")
      println()
    }
  }
}
