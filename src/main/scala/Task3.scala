import java.io.File

object Task3 extends App {
  print("Введите путь к каталогу (или Enter для текущего): ")

  val path = scala.io.StdIn.readLine().trim
  val root = if (path.isEmpty) new File(".") else new File(path)

  if (!root.exists() || !root.isDirectory) {
    println(s"Ошибка: '$path' не является каталогом.")
  } else {
    def allFiles(dir: File): LazyList[File] = {
      val children: Array[File] = Option(dir.listFiles()).getOrElse(Array.empty[File])
      children.to(LazyList) #::: children.filter(_.isDirectory).to(LazyList).flatMap(allFiles)
    }

    val files = allFiles(root)

    println(s"\nВсего найдено: ${files.size}")
    println("-" * 80)
    files.foreach { f =>
      val typ  = if (f.isDirectory) "Папка" else "Файл"
      val size = if (f.isFile) s"${f.length()} байт" else "-"
      println(f"${f.getPath}%-60s $size%-10s $typ")
    }
  }
}