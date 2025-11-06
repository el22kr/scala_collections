import java.io.File
import scala.io.Source
import scala.util.Using

object Task4 extends App {
  val poemsDir = new File(s"src/main/resources/data/pushkin")

  if (!poemsDir.exists() || !poemsDir.isDirectory) {
    println(s"Ошибка: каталог с стихотворениями не найден.")
  } else {
    val poemFiles = poemsDir.listFiles().filter(f => f.isFile && f.getName.endsWith(".txt")).toList

    if (poemFiles.isEmpty) {
      println(s"В папке '${poemsDir.getPath}' нет стихотворений (.txt)")
    } else {
      val allLines = poemFiles.flatMap { file =>
        Using(Source.fromFile(file, "UTF-8"))(_.getLines().toList)
          .getOrElse(List.empty[String])
      }.filter(_.nonEmpty)

      if (allLines.isEmpty) {
        println("Файлы стихотворений пусты.")
      } else {
        println(s"\nНайдено ${allLines.size} строк из ${poemFiles.size} стихотворений")

        val sorted = allLines.sortBy(_.length)

        println("\nСтроки, отсортированные по возрастанию длины:")
        sorted.foreach(line => println(f"${line.length}%3d | $line"))


        val lengths = sorted.map(_.length)
        val avg = lengths.sum / lengths.size
        println(f"  Всего строк: ${lengths.size}")
        println(f"  Минимальная длина: ${lengths.min}")
        println(f"  Максимальная длина: ${lengths.max}")
        println(f"  Средняя длина: $avg")
      }
    }
  }
}