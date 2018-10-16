
import java.io.File
import java.io.FileNotFoundException
import java.util.Random

fun main(vararg: Array<String>) {
    try {
        val list = File("task/dictionary.txt").readLines()
        var nextGame = true

        println("Welcome to the Bulls and Cows Game!")
        while (nextGame) {
            val currentWord = list[Random().nextInt(list.size)]
            println("I offered a " + currentWord.length + "-letter word, your guess?")
            val check = Checker(patternWord = currentWord)
            check.start()
            println("Wanna play again? Y/N")
            nextGame = check.exitCheck()
        }
    } catch (ex: FileNotFoundException) {
        println("Check path for dictionary.txt!!")
    } catch (ex: Exception) {
        println("Problem not in dictionary.txt path")
    }
}