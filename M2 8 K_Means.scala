import breeze.linalg._
import breeze.stats.distributions.Rand
import com.github.tototoshi.csv._

import java.io.File

object Abhay_8_K_Mean {

  def main(args: Array[String]): Unit = {

    // Read river dataset
    val reader = CSVReader.open(new File("RIVER_PH_TDS.csv"))
    val rows = reader.allWithHeaders()
    reader.close()

    // Features: pH and TDS
    val data = DenseMatrix(
      rows.map { row =>
        Array(
          row("pH").toDouble,
          row("TDS").toDouble
        )
      }: _*
    )

    val numSamples = data.rows
    val numFeatures = data.cols
    val k = 2
    val maxIterations = 100

    println("River dataset loaded successfully.")
    println(s"Number of samples: $numSamples")
    println(s"Number of features: $numFeatures")
    println(s"Number of clusters: $k")

    // 1. Initialize centroids
    var centroids =
      DenseMatrix.zeros[Double](k, numFeatures)

    val randomIndices =
      (0 until data.rows)
        .sortBy(_ => Rand.uniform.get)
        .take(k)

    for (i <- 0 until k) {
      for (j <- 0 until numFeatures) {
        centroids(i, j) = data(randomIndices(i), j)
      }
    }

    println(s"\nInitial centroids:\n$centroids")

    var assignments =
      DenseVector.zeros[Int](data.rows)

    var previousAssignments =
      DenseVector.fill(data.rows)(-1)

    var iteration = 0
    var converged = false

    // Main K-Means loop
    while (iteration < maxIterations && !converged) {

      println(s"\n--- Iteration ${iteration + 1} ---")

      // 2. Assignment Step
      for (i <- 0 until data.rows) {

        val point = data(i, ::).t

        var minDistance = Double.MaxValue
        var closestCentroidIndex = -1

        for (j <- 0 until k) {

          val centroid = centroids(j, ::).t

          val dist =
            euclideanDistance(point, centroid)

          if (dist < minDistance) {
            minDistance = dist
            closestCentroidIndex = j
          }
        }

        assignments(i) = closestCentroidIndex
      }

      // Check convergence
      if (assignments == previousAssignments) {
        converged = true
      } else {
        previousAssignments = assignments.copy
      }

      // 3. Update Step
      val newCentroids =
        DenseMatrix.zeros[Double](k, numFeatures)

      val clusterCounts =
        DenseVector.zeros[Int](k)

      // Add points to their respective clusters
      for (i <- 0 until data.rows) {

        val clusterId = assignments(i)

        for (j <- 0 until numFeatures) {
          newCentroids(clusterId, j) += data(i, j)
        }

        clusterCounts(clusterId) += 1
      }

      // Calculate mean for each cluster
      for (i <- 0 until k) {

        if (clusterCounts(i) > 0) {

          for (j <- 0 until numFeatures) {
            newCentroids(i, j) =
              newCentroids(i, j) /
                clusterCounts(i).toDouble
          }
        }
      }

      centroids = newCentroids

      println(s"Updated centroids:\n$centroids")

      iteration += 1
    }

    // Final Results
    println("\n--- Final Results ---")

    println(
      s"K-means algorithm converged in $iteration iterations."
    )

    println(s"\nFinal centroids:\n$centroids")

    println(
      s"\nFinal cluster assignments:\n$assignments"
    )

    // Cluster counts
    for (i <- 0 until k) {

      val count =
        (0 until data.rows).count(
          row => assignments(row) == i
        )

      println(
        s"Cluster $i contains $count rivers."
      )
    }
  }
}
