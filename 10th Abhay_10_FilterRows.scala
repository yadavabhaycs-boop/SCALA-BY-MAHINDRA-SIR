// 120 ABHAY YADAV
import com.github.tototoshi.csv._
import java.io.File


object Abhay_10_FilterRows {


 def main(args: Array[String]): Unit = {


   val reader = CSVReader.open(new File("students.csv"))


   val data = reader.allWithHeaders()


   reader.close()


   val threshold = 80


   // Filter rows where Maths marks > 80
   val filteredRows = data.filter { row =>
     row.get("Maths").exists(value =>
       value.toIntOption.exists(_ > threshold)
     )
   }


   println(s"\nTotal Students with Maths Marks > $threshold : ${filteredRows.length}\n")


   // Print filtered rows
   filteredRows.foreach { row =>
     println(row.values.mkString(", "))
   }


 }
}


