import scala.io.Source
import java.io.{File, PrintWriter}
import scala.util.Using

object Task1 extends App {
  val inputFile  = "src/main/resources/data/input.txt"
  val outputFile = "src/main/resources/data/output_reversed.txt"

  val lines = Using(Source.fromFile(inputFile))(_.getLines().toList)
    .getOrElse {
      println(s"Ошибка чтения файла: $inputFile")
      List.empty[String]
    }

  if (lines.nonEmpty) {
    println("Исходные строки:")
    lines.foreach(println)

    val reversed = lines.reverse
    Using(new PrintWriter(new File(outputFile))) { writer =>
      reversed.foreach(writer.println)
    }

    //println("\nСтроки в обратном порядке записаны в:")
    //println(outputFile)

    println("\nРезультат:")
    reversed.foreach(println)
  } else {
    println("Файл пуст или не найден.")
  }
}