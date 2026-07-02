//S123 ABHAY YADAV
import breeze.linalg._
import breeze.stats._

object CricketVector {

  def main(args: Array[String]): Unit = {

    // Create Dense Vectors
    val runs1 = DenseVector(10.0, 20.0, 30.0, 40.0, 50.0)
    val runs2 = DenseVector(15.0, 25.0, 35.0, 45.0, 55.0)

    // Display Vectors
    println("Player 1 Runs: " + runs1)
    println("Player 2 Runs: " + runs2)

    // Sum of Player 1 Runs
    val total = breeze.linalg.sum(runs1)
    println("Sum = " + total)

    // Mean of Player 1 Runs
    val average = breeze.stats.mean(runs1)
    println("Mean = " + average)

    // Dot Product
    val dotProduct = runs1 dot runs2
    println("Dot Product = " + dotProduct)

  }

}

