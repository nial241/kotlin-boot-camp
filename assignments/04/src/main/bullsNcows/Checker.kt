class Checker(private var usrWord: String? = null, private val patternWord: String) {

    private fun stringIsWord(): Boolean {
        print("> ")
        try {
            usrWord = readLine()
        } catch (e: Exception) {
            usrWord ?: return false
            println("Problems with user word input")
            return false
        }
        return (usrWord!!.toLowerCase().all { it.isLetter() } && usrWord!!.length == patternWord.length)
    }

    internal fun start() {
        var attempts = 0
        var win = false
        while (!win && attempts < 10) {
            win = check()
            if (!win) attempts++
        }
        when {
            win -> println("You won!")
            else -> println("You loose... 10 attempts were made/n Word was $patternWord ")
        }
    }

    internal fun exitCheck(): Boolean {
        try {
            usrWord = readLine()
        } catch (e: Exception) {
            println("Something wrong with user input")
            return false
        }

        return when (usrWord?.toUpperCase()) {
            "Y" -> true
            "N" -> false
            else -> {
                println("Input y or n")
                exitCheck()
            }
        }
    }

    private fun check(): Boolean {
        try {
            while (!stringIsWord()) {
                println("Word must contains only letters and word len must be ${patternWord.length}")
            }
        } catch (e: Exception) {
            println("Something wrong with user input")
            return false
        }
        usrWord ?: return false
        if (patternWord == usrWord!!.toLowerCase()) return true

        val cows: Int = patternWord.fold(0) { acc, it -> if (usrWord!!.contains(it))acc + 1 else acc + 0 }
        val bulls: Int = patternWord.foldIndexed(0) { index, acc, it -> if (usrWord!![index] == it) acc + 1 else acc }

        println("Bulls: $bulls")
        println("Cows: $cows")
        return false
    }
}
