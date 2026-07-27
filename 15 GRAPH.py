import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Abhay_15_RiverLinePlot {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("river_level.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Read Date and Water Level
    val parsedData = data.flatMap { row =>
      try {
        val date = LocalDate.parse(row("Date"), formatter)
        val level = row("WaterLevel").toDouble
        Some((date, level))
      } catch {
        case _: Throwable => None
      }
    }.sortBy(_._1)

    // X-axis (Time)
    val x = DenseVector((0 until parsedData.length).map(_.toDouble).toArray)

    // Y-axis (Water Level)
    val y = DenseVector(parsedData.map(_._2).toArray)

    val fig = Figure("River Water Level Trend")

    val plt = fig.subplot(0)

    plt += plot(x, y, name = "Water Level", colorcode = "blue")

    plt.xlabel = "Days"

    plt.ylabel = "Water Level"

    plt.title = "River Water Level Over Time"

    fig.refresh()
  }
}
