import breeze.linalg.{DenseVector, euclideanDistance}

object Abhay_7_KNN {

  // Data point containing river name, features and water quality
  private case class DataPoint(
                        river: String,
                        features: DenseVector[Double],
                        label: String
                      )

  def main(args: Array[String]): Unit = {

    // River water dataset
    // Features: pH, TDS
    val dataset = Seq(
      DataPoint("Ganga", DenseVector(7.2, 210.0), "Good"),
      DataPoint("Yamuna", DenseVector(7.0, 320.0), "Average"),
      DataPoint("Godavari", DenseVector(7.5, 195.0), "Good"),
      DataPoint("Krishna", DenseVector(6.9, 410.0), "Poor"),
      DataPoint("Kaveri", DenseVector(7.4, 220.0), "Good"),
      DataPoint("Narmada", DenseVector(7.1, 280.0), "Average"),
      DataPoint("Tapti", DenseVector(6.8, 430.0), "Poor")
    )

    println("Training Data:")

    dataset.foreach { point =>
      println(
        s"River: ${point.river}, Features: ${point.features}, Label: ${point.label}"
      )
    }

    // New river water sample to classify
    val newPointFeatures = DenseVector(7.1, 300.0)

    println(
      s"\nNew data point to classify: $newPointFeatures"
    )

    // Initialize minimum distance
    var minDistance = Double.MaxValue
    var predictedLabel = ""
    var nearestRiver = ""

    // Calculate distance from new point to every river
    for (point <- dataset) {

      val dist = euclideanDistance(
        newPointFeatures,
        point.features
      )

      println(
        s"Distance to ${point.river} (${point.label}): $dist"
      )

      // Find nearest neighbor
      if (dist < minDistance) {
        minDistance = dist
        predictedLabel = point.label
        nearestRiver = point.river
      }
    }

    // Display classification result
    println("\nClassification Result:")

    println(
      s"Nearest River: $nearestRiver"
    )

    println(
      s"Nearest Distance: $minDistance"
    )

    println(
      s"Predicted Water Quality: $predictedLabel"
    )
  }
}
