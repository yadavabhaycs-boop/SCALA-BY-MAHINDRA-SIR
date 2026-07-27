import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._

import java.io.File

object Abhay_14_RiverHistogram {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("river_histogram.csv"))

    val data = reader.allWithHeaders()

    reader.close()

    // Extract TDS values
    val tdsValues =
      DenseVector(data.map(_("TDS").toDouble).toArray)

    // Create Figure
    val fig = Figure("Histogram of River TDS")

    val binSizes = List(5, 10, 20)

    for ((bins, idx) <- binSizes.zipWithIndex) {

      val plt = fig.subplot(1, binSizes.length, idx)

      plt += hist(tdsValues, bins)

      plt.title = s"Histogram ($bins bins)"

      plt.xlabel = "TDS"

      plt.ylabel = "Frequency"
    }
    fig.refresh()
  }
}
