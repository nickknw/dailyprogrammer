import scala.io.Source._

object WordLadder {

    val dictFile = "../selected_four-letter_words.txt"

    // Word Ladder

    def wordLadder(word: String, dict: Seq[String]): Seq[String] = 
        dict.filter(oneLetterOff(word, _))


    private def oneLetterOff(word1: String, word2: String): Boolean = 
        (word1, word2).zipped.map(_ == _).filter(x => !x).length == 1

    // Most Ladderable

    def mostLadderable(num: Int, dict: Seq[String]): Seq[(String, Int)] = {
        val wordNumberPairs = for (w <- dict) yield (w, wordLadder(w, dict).length)
        wordNumberPairs.sortBy(_._2).reverse.take(num)
    }

    // Word Chain

    def wordChain(steps: Int, current: String, dict: Seq[String]): Set[String] = {
        var finalSet = Set(current)
        var next = nextLevelOfWords(finalSet, dict)

        for (i <- 0 until steps) {
            finalSet = finalSet ++ next
            next = nextLevelOfWords(next, dict)
        }

        return finalSet
    }

    private def nextLevelOfWords(current: Set[String], dict: Seq[String]): Set[String] = 
        current.flatMap(w => wordLadder(w, dict))


    // Command line interface

    val usage = "usage info"

    def fmtList(list: Seq[String]): String = list.mkString("\n")

    def fmtPairs(pairs: Seq[(String, Int)]): String = pairs.flatMap(p => p._1 + " - " + p._2 + "\n").mkString

    def main(args: Array[String]): Unit = {
        val dict = fromFile(dictFile).getLines.toSeq

        args toList match {
            case "list" :: word :: Nil            => println(fmtList(wordLadder(word, dict)))
            case "top" :: num :: Nil              => println(fmtPairs(mostLadderable(num.toInt, dict)))
            case "chain" :: steps :: start :: Nil => println(wordChain(steps.toInt, start, dict).size)
            case _                                => println(usage)
        }
    }
}

