import scala.io.Source._

object WordLadder {

    val dictFile = "../selected_four-letter_words.txt"

    // Word Ladder

    def wordLadder(word: String, dict: Iterator[String]): Seq[String] = {
        return List("hello")
    }

    // Most Ladderable

    def mostLadderable(num: Int, dict: Iterator[String]): Seq[(String, Int)] = {
        return List(("tmp", 1))
    }

    // Word Chain

    def wordChain(steps: Int, current: String, dict: Iterator[String]): Seq[String] = {
        return List("tmp")
    }

    // Command line interface

    val usage = "usage info"

    def fmtList(list: Seq[String]): String = list.mkString("\n")

    def fmtPairs(pairs: Seq[(String, Int)]): String = pairs.flatMap(p => p._1 + " - " + p._2 + "\n").mkString

    def main(args: Array[String]): Unit = {
        val dict = fromFile(dictFile).getLines

        args toList match {
            case "list" :: word :: Nil            => println(fmtList(wordLadder(word, dict)))
            case "top" :: num :: Nil              => println(fmtPairs(mostLadderable(num.toInt, dict)))
            case "chain" :: steps :: start :: Nil => println(wordChain(steps.toInt, start, dict).length)
            case _                                => println(usage)
        }
    }
}

