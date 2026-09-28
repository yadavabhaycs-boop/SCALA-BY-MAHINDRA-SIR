object PolynomialFeatures {

  def main(args: Array[String]): Unit = {

    val numbers = List(1, 2, 3)
    val degree = 3

    val polynomialFeatures = numbers.flatMap { x =>
      (1 to degree).map(power => Math.pow(x, power).toInt)
    }

    println("Original Data:")
    println(numbers)

    println("\nPolynomial Features up to Degree 3:")
    println(polynomialFeatures)
  }
}
