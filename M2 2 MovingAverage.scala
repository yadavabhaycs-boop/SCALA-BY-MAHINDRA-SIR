import scala.io.Source
import scala.math.pow

object MovingAverage {

  def main(args: Array[String]): Unit = {

    // Import dataset
    val file = Source.fromFile("DailySales.csv")
    val lines = file.getLines().drop(1).toList
    file.close()

    // Feature Engineering: extract Date and Sales
    val data = lines.map { line =>
      val values = line.split(",")
      (values(0), values(1).toDouble)
    }

    val dates = data.map(_._1)
    val sales = data.map(_._2)

    val window = 3

    // Simple Moving Average (SMA)
    val sma = sales.sliding(window).map { values =>
      values.sum / window
    }.toList

    // Weighted Moving Average (WMA)
    // Weights: 1, 2, 3
    val weights = List(1, 2, 3)
    val weightSum = weights.sum

    val wma = sales.sliding(window).map { values =>
      values.zip(weights).map {
        case (value, weight) => value * weight
      }.sum / weightSum
    }.toList

    // Exponential Moving Average (EMA)
    val alpha = 2.0 / (window + 1)

    val ema = sales.tail.foldLeft(List(sales.head)) {
      (result, currentValue) =>
        val previousEMA = result.last
        val currentEMA =
          alpha * currentValue + (1 - alpha) * previousEMA

        result :+ currentEMA
    }

    // Display original data
    println("========== DAILY SALES ==========")

    data.foreach {
      case (date, sale) =>
        println(f"$date : $sale%.2f")
    }

    // Display SMA
    println("\n========== SIMPLE MOVING AVERAGE (SMA) ==========")

    sma.zipWithIndex.foreach {
      case (value, index) =>
        println(
          f"${dates(index + window - 1)} : $value%.2f"
        )
    }

    // Display WMA
    println("\n========== WEIGHTED MOVING AVERAGE (WMA) ==========")

    wma.zipWithIndex.foreach {
      case (value, index) =>
        println(
          f"${dates(index + window - 1)} : $value%.2f"
        )
    }

    // Display EMA
    println("\n========== EXPONENTIAL MOVING AVERAGE (EMA) ==========")

    ema.zipWithIndex.foreach {
      case (value, index) =>
        println(
          f"${dates(index)} : $value%.2f"
        )
    }

    // Inference
    println("\n========== INFERENCE ==========")

    println("SMA provides a simple smoothing of the sales data.")
    println("WMA gives more importance to recent sales values.")
    println("EMA responds faster to recent changes in sales.")

    println("\n========== JUSTIFICATION ==========")

    println(
      "EMA is considered the most suitable for this dataset because " +
      "it gives higher importance to recent sales and captures changes " +
      "in the sales trend more quickly."
    )
  }
}
