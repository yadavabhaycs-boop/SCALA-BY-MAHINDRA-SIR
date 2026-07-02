// S123 ABHAY YADAV
import scala.util.Random

object VarianceStdDev {

  def main(args: Array[String]): Unit = {

    // Generate 10 random numbers (1 to 100)
    val numbers = List.fill(10)(Random.nextInt(100) + 1)

    // Display dataset
    println("Random Numbers = " + numbers)

    // Calculate Mean
    val mean = numbers.sum.toDouble / numbers.length

    // Calculate Variance
    val variance = numbers.map(x => math.pow(x - mean, 2)).sum / numbers.length

    // Calculate Standard Deviation
    val stdDeviation = math.sqrt(variance)

    // Display Results
    println("Mean = " + mean)
    println("Variance = " + variance)
    println("Standard Deviation = " + stdDeviation)

  }
}

