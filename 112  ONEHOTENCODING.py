import com.github.tototoshi.csv._
import java.io.File

object Abhay_12_OneHotEncoding {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("river_water.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Get unique Water Quality values
    val categories = data.map(_("WaterQuality")).distinct.sorted

    val newData = data.map { row =>

      val quality = row("WaterQuality")

      val oneHot = categories.map { cat =>
        cat -> (if (cat == quality) "1" else "0")
      }.toMap

      row -- Seq("WaterQuality") ++ oneHot
    }

    // Print Header
    val headers = newData.head.keys.toList
    println(headers.mkString(", "))

    // Print Encoded Data
    newData.foreach { row =>
      println(headers.map(row).mkString(", "))
    }

    // Save New CSV
    val writer = CSVWriter.open(new File("river_water_encoded.csv"))

    writer.writeRow(headers)

    newData.foreach { row =>
      writer.writeRow(headers.map(row))
    }

    writer.close()

    println("\nOne-Hot Encoded file written to river_water_encoded.csv")
  }
}
