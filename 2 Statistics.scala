// S123 ABHAY YADAV
object Statistics {
  def main(args: Array[String]): Unit = {
    val numbers = List(20, 40, 60, 70, 30)

    // 1.Mean
    val mean = numbers.sum.toDouble / numbers.length

    // 2.Median
    val sorted = numbers.sorted
    val median = sorted(sorted.length / 2)

    // 3.Mode
    val mode = numbers.groupBy(identity).maxBy(_._2.size)._1

    println("Numbers = " + numbers)
    println("Mean = " + mean)
    println("Median = " + median)
    println("Mode = " + mode)
  }
}
