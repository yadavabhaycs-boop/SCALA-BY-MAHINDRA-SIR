import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Abhay_16_CombinedPlot {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("river_level.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Parse Date and Water Level
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val parsedData = data.flatMap { row =>
      try {
        val date = LocalDate.parse(row("Date"), formatter)
        val level = row("WaterLevel").toDouble
        Some((date, level))
      } catch {
        case _: Throwable => None
      }
    }.sortBy(_._1)

    // Convert to Breeze Vectors
    val x = DenseVector((0 until parsedData.length).map(_.toDouble).toArray)

    val y = DenseVector(parsedData.map(_._2).toArray)

    // Create Figure
    val fig = Figure("River Water Level - Line + Scatter")

    val plt = fig.subplot(0)

    // Line Plot
    plt += plot(x, y, name = "Water Level Line", colorcode = "blue")

    // Scatter Plot
    plt += plot(x, y, '.', name = "Water Level Points", colorcode = "red")

    plt.xlabel = "Days"

    plt.ylabel = "Water Level"

    plt.title = "River Water Level - Line + Scatter"

    fig.refresh()
  }
}
