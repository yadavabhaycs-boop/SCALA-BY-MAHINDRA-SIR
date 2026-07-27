import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._

import java.io.File

object Abhay_13_ScatterPlot {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("river_plot.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Separate data by Quality
    val good = data.filter(_("Quality") == "Good")
    val average = data.filter(_("Quality") == "Average")
    val poor = data.filter(_("Quality") == "Poor")

    def extractXY(rows: List[Map[String, String]]) = {

      val x = DenseVector(rows.map(_("pH").toDouble).toArray)

      val y = DenseVector(rows.map(_("TDS").toDouble).toArray)

      (x, y)
    }

    val (xGood, yGood) = extractXY(good)
    val (xAverage, yAverage) = extractXY(average)
    val (xPoor, yPoor) = extractXY(poor)

    val fig = Figure()

    val plt = fig.subplot(0)

    plt.title = "River Water Quality"

    plt.xlabel = "pH"

    plt.ylabel = "TDS"

    plt += plot(xGood, yGood, '.', name = "Good", colorcode = "blue")

    plt += plot(xAverage, yAverage, '.', name = "Average", colorcode = "green")

    plt += plot(xPoor, yPoor, '.', name = "Poor", colorcode = "red")

    fig.refresh()
  }
}

