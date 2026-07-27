import scala.io.Source

object WordFrequencyCounter {

  def main(args: Array[String]): Unit = {

    val filename = "abhay.txt"

    // Read the file
    val lines = Source.fromFile(filename).getLines().toList

    // Tokenize and normalize words
    val words = lines
      .flatMap(_.toLowerCase.split("\\W+"))
      .filter(_.nonEmpty)

    // Count word frequencies
    val wordCounts = words.groupBy(identity).view.mapValues(_.size).toMap

    // Print frequencies
    println("Word Frequencies:")

    wordCounts.toSeq.sortBy(-_._2).foreach {
      case (word, count) =>
        println(f"$word%-15s -> $count")
    }

  }
}
