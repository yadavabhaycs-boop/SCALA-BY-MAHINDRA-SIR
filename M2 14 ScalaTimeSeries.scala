import scala.util.Random

object ScalaTimeSeries {

  def main(args: Array[String]): Unit = {

    val random = new Random()

    // Generate 30 days of synthetic sales data
    val salesData = (1 to 30).map { day =>
      val sales = 1000 + random.nextInt(2001)
      (day, sales)
    }

    println("===== DAILY SALES DATA =====")

    salesData.foreach {
      case (day, sales) =>
        println(s"Day $day : ₹$sales")
    }

    // Total Sales
    val totalSales = salesData.map(_._2).sum

    // Average Sales
    val averageSales = salesData.map(_._2).sum.toDouble / salesData.size

    // Maximum Sales
    val maxSale = salesData.maxBy(_._2)

    // Minimum Sales
    val minSale = salesData.minBy(_._2)

    println("\n===== TIME SERIES ANALYSIS =====")

    println(s"Total Sales      : ₹$totalSales")
    println(f"Average Sales    : ₹$averageSales%.2f")
    println(s"Maximum Sales    : Day ${maxSale._1} = ₹${maxSale._2}")
    println(s"Minimum Sales    : Day ${minSale._1} = ₹${minSale._2}")
  }
}
