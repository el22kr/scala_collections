import scala.io.StdIn
import scala.util.Try

object Task2 extends App {
  print("Введите число: ")

  val result = for {
    input  <- Option(StdIn.readLine())
    number <- Try(input.toLong).toOption
  } yield {
    val reversed = number.toString.reverse.toLong
    s"Исходное число: $number\nЧисло с обратным порядком цифр: $reversed"
  }

  println(result.getOrElse("Ошибка: Введено не число!"))
}