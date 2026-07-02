//S123 ABHAY YADAV
import breeze.linalg._
import scala.util.Random

object MatrixTranspose {

  def main(args: Array[String]): Unit = {

    // Matrix size
    val rows = 3
    val cols = 3

    // Generate random integers (1 to 10)
    val randData = Array.fill(rows * cols)(Random.nextInt(10) + 1)

    // Create DenseMatrix
    val randMatrix = new DenseMatrix(rows, cols, randData)

    // Display Random Matrix
    println(s"Random Integer Matrix:\n$randMatrix")

    // Transpose
    val transposed = randMatrix.t
    println(s"\nTransposed Matrix:\n$transposed")

    // Convert to Double for determinant calculation
    val doubleMatrix = randMatrix.map(_.toDouble)

    // Determinant
    val determinant = det(doubleMatrix)
    println(f"\nDeterminant: $determinant%.2f")

  }

}
