// S123 ABHAY YADAV


import breeze.linalg._


object P7FootballMatrix {


 def main(args: Array[String]): Unit = {


   // Goals scored by Team A and Team B
   val teamA = DenseMatrix(
     (2.0, 1.0),
     (3.0, 4.0)
   )


   val teamB = DenseMatrix(
     (1.0, 2.0),
     (4.0, 2.0)
   )


   // Element-wise operations
   val addition = teamA + teamB
   val subtraction = teamA - teamB
   val multiplication = teamA *:* teamB
   val division = teamA /:/ teamB


   // Display matrices
   println("Team A Goals:")
   println(teamA)


   println("\nTeam B Goals:")
   println(teamB)


   // Addition
   println("\nAddition:")
   println(addition)


   // Subtraction
   println("\nSubtraction:")
   println(subtraction)


   // Element-wise Multiplication
   println("\nElement-wise Multiplication:")
   println(multiplication)


   // Element-wise Division
   println("\nElement-wise Division:")
   println(division)


 }
}
