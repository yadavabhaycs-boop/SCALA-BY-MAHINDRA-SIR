//S123 ABHAY YADAV
import breeze.linalg._
import scala.util.Random

object FootballMatrix {

  def main(args: Array[String]): Unit = {

    val rows = 3
    val cols = 3

    // Generate a 3x3 matrix of random football goals (1 to 5)
    val goalData = Array.fill(rows * cols)(Random.nextInt(5) + 1)

    val goalMatrix = new DenseMatrix(rows, cols, goalData)

    println(s"Football Goals Matrix:\n$goalMatrix")

    // Transpose
    val transposed = goalMatrix.t
    println(s"\nTranspose Matrix:\n$transposed")

    // Convert to Double for determinant calculation
    val doubleMatrix = goalMatrix.map(_.toDouble)

    val determinant = det(doubleMatrix)
    println(f"\nDeterminant: $determinant%.2f")
  }
}
  
