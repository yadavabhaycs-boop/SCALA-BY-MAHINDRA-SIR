// S123 ABHAY YADAV

import breeze.linalg._

object StudentMarks {

  def main(args: Array[String]): Unit = {

    // Marks of 4 students in 4 subjects
    val marks = DenseMatrix(
      (70, 80, 90, 60),
      (65, 75, 85, 95),
      (88, 78, 68, 58),
      (92, 82, 72, 62)
    )

    println(s"Student Marks:\n$marks")

    // Select Student 2 & 3 and Subject 2 to 4
    val selected = marks(1 to 2, 1 to 3)

    println(s"\nSelected Marks:\n$selected")

    // Row Sums (Total marks of selected students)
    val rowSums = sum(selected(*, ::))
    println(s"\nStudent Total Marks: $rowSums")

    // Column Sums (Total marks of selected subjects)
    val colSums = sum(selected(::, *))
    println(s"Subject Total Marks: $colSums")
  }
}
