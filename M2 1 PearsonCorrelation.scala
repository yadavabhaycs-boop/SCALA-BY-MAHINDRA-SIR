import scala.io.Source
import scala.math.sqrt

object PearsonCorrelation {

  def main(args: Array[String]): Unit = {

    // Import dataset
    val file = Source.fromFile("StudyScore.csv")
    val data = file.getLines().drop(1).toList
    file.close()

    // Create two lists
    val studyHours = data.map(_.split(",")(0).toDouble)
    val examScores = data.map(_.split(",")(1).toDouble)

    val n = studyHours.length

    // Required calculations
    val sumX = studyHours.sum
    val sumY = examScores.sum

    val sumXY = studyHours.zip(examScores)
      .map { case (x, y) => x * y }
      .sum

    val sumX2 = studyHours.map(x => x * x).sum
    val sumY2 = examScores.map(y => y * y).sum

    // Pearson Correlation Coefficient
    val numerator = n * sumXY - (sumX * sumY)

    val denominator = sqrt(
      (n * sumX2 - sumX * sumX) *
      (n * sumY2 - sumY * sumY)
    )

    val r = numerator / denominator

    // T-test
    val degreesOfFreedom = n - 2
    val tValue = r * sqrt(degreesOfFreedom) / sqrt(1 - r * r)

    // Output
    println("========== PEARSON CORRELATION ==========")
    println(s"Number of observations : $n")
    println(f"Pearson Correlation (r): $r%.4f")

    if (r > 0)
      println("Relationship           : Positive")
    else if (r < 0)
      println("Relationship           : Negative")
    else
      println("Relationship           : No linear relationship")

    println("\n========== SIGNIFICANCE TEST ==========")
    println(s"Degrees of Freedom     : $degreesOfFreedom")
    println(f"T-value                : $tValue%.4f")
    println("Significance Level     : 5%")
    println("Critical T-value       : 2.306")

    if (math.abs(tValue) > 2.306)
      println("Inference              : Correlation is statistically significant.")
    else
      println("Inference              : Correlation is not statistically significant.")

    println("\n========== CONCLUSION ==========")

    if (r > 0 && math.abs(tValue) > 2.306)
      println("There is a strong positive and statistically significant relationship between study hours and exam scores.")
    else if (r < 0 && math.abs(tValue) > 2.306)
      println("There is a strong negative and statistically significant relationship between study hours and exam scores.")
    else
      println("The correlation is not statistically significant.")
  }
}
