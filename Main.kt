package sorting
import java.io.File
import java.util.InputMismatchException
import java.util.Scanner
import kotlin.system.exitProcess
var fileInputName =""
var fileOutputName =""
var InputType = 0
var OutputType = 0

val typeList  = listOf(
    listOf("-sortingType", "natural", "byCount"),
    listOf("-dataType", "long", "line", "word"),
    listOf("-inputFile", "-outputFile")
)

fun checkArgs(args: Array<String>, typeList: List<List<String>>) {
    if (args.contains(typeList[0][0])) {
        if (args.indexOf(typeList[0][0]) + 1 < args.size) {
            for (i in 1..2) {
                if (args[(args.indexOf(typeList[0][0]) + 1)] == typeList[0][i]) break
                if (i == typeList[0].lastIndex) {
                    println("No sorting type defined!")
                    exitProcess(0)
                }
            }
        } else {
            println("No sorting type defined")
            exitProcess(0)
        }
    }
    if (args.contains(typeList[1][0])) {
        if (args.indexOf(typeList[1][0]) + 1 < args.size) {
            for (i in 1..3) {
                if (args[(args.indexOf(typeList[1][0]) + 1)] == typeList[1][i]) break
                if (i == typeList[1].lastIndex) {
                    println("No data type defined!")
                    exitProcess(0)
                }
            }
        } else {
            println("No data type defined!")
            exitProcess(0)
        }
    }
    if (args.contains(typeList[2][0])) {
        try {
            fileInputName = (args[(args.indexOf(typeList[2][0]) + 1)])
            InputType = 1
        } catch (e: IndexOutOfBoundsException) {
            println("No input file name defined!")
            exitProcess(0)}

    }
    if (args.contains(typeList[2][1])) {
        try {
            fileOutputName = (args[(args.indexOf(typeList[2][1]) + 1)])
            OutputType = 1
        } catch (e: IndexOutOfBoundsException) {
            println("No output file name defined!")
            exitProcess(0)}

    }
    val wrongArgs = args.filterNot { typeList.flatten().contains(it) }.toMutableList()
    if (wrongArgs.isEmpty()) return else {
        wrongArgs.remove(fileInputName)
        wrongArgs.remove(fileOutputName)
        for (i in wrongArgs) {
            println("\"$i\" is not a valid parameter. It will be skipped.")
        }
    }
}

fun main(args: Array<String> = arrayOf()) {
    checkArgs(args, typeList)
    val scanner = Scanner(System.`in`)
    val notSortedString: MutableList<String> = mutableListOf()
    val notSortedInt: MutableList<Int> = mutableListOf()
    when { // data type
        args.contains("word") -> notSortedString.addAll(inputWord(scanner))
        args.contains("line") -> notSortedString.addAll(inputLine(scanner))
        else -> notSortedInt.addAll(inputNumbers(scanner))
    }
        sorting(notSortedString, notSortedInt, args)


}

fun inputLine(scanner: Scanner): MutableList<String> {
    val lineList: MutableList<String> = mutableListOf()
    if (InputType != 1) {
        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            lineList.add(line)
        }
        scanner.close()
        return lineList
    } else {
        val fileInput = File(fileInputName).readLines()
            lineList.addAll(fileInput)
        }
        scanner.close()
        return lineList
    }

fun inputWord(scanner: Scanner = Scanner(System.`in`)): MutableList<String> {
    val wordList: MutableList<String> = mutableListOf()
    if (InputType != 1) {
    while (scanner.hasNext()) {
        val input = scanner.next()
        wordList.add(input)
    }
    }
    else {
        val fileInput = File(fileInputName).readLines().flatMap { it.split(" ") }.filter { it.isNotBlank() }
        wordList.addAll(fileInput)
        println(fileInput)
        }
    scanner.close()
    return wordList
}

fun inputNumbers (scanner: Scanner = Scanner(System.`in`)): MutableList<Int> {
    val wrongInputList: MutableList<String> = mutableListOf()
    val numberList: MutableList<Int> = mutableListOf()
    if (InputType != 1) {
        while (scanner.hasNext()) {
            try {
                val input = scanner.nextInt()
                numberList.add(input)
            } catch (e: InputMismatchException) {
                wrongInputList.add(scanner.next())
            }
        }

    } else {
            val fileInput = File(fileInputName).readLines().flatMap { it.split(" ") }.filter { it.isNotBlank() }
            for (i in fileInput) {
                try {
                    numberList.add(i.toInt())
                } catch (e: Exception) { wrongInputList.add(i) }
            }
    }
    if (wrongInputList.isNotEmpty()) {
        for (i in wrongInputList) {
            println("\"$i\" is not a long. It will be skipped.")
        }
    }
    scanner.close()
    return numberList
}


fun natural(args: Array<String> = arrayOf(), sortedList: List<String>) {
    if (args.contains("line")) {
        if (OutputType != 1) {
            println("Sorted data:")
            println(sortedList.joinToString("\n"))
        } else {
            File(fileOutputName).appendText("\nSorted data:")
            File(fileOutputName).appendText(sortedList.joinToString("\n"))
        }
    } else {
        if (OutputType != 1) {
            println("Sorted data: ${sortedList.joinToString(" ")}")
        } else File(fileOutputName).appendText("\nSorted data: ${sortedList.joinToString(" ")}")

    }
}

fun sorting(stringList: MutableList<String>, numberList: MutableList<Int>, args: Array<String> = arrayOf()) {
    if (numberList.isNotEmpty()) {
        val sortedListInt = numberList.sorted()
        val sortedList = sortedListInt.map { it.toString() }
        if (OutputType != 1) {
            println("Total numbers: ${sortedListInt.size}.")
        } else File(fileOutputName).writeText("Total numbers: ${sortedListInt.size}.")
        if (args.contains("byCount")) byCount(sortedList) else natural(args, sortedList)
        }
    if (stringList.isNotEmpty() && !args.contains("line")) {
        val sortedList = stringList.sorted()
        if (OutputType != 1) {
            println("Total words: ${sortedList.size}.")
        } else File(fileOutputName).writeText("Total words: ${sortedList.size}.")
        if (args.contains("byCount")) byCount(sortedList) else natural(args, sortedList)
        }
    if (stringList.isNotEmpty() && args.contains("line")) {
        val sortedList = stringList.sortedBy { it.length }
        if (OutputType != 1) {
            println("Total lines: ${sortedList.size}.")
        } else File(fileOutputName).writeText("Total lines: ${sortedList.size}.")
        if (args.contains("byCount")) byCount(sortedList, args) else natural(args, sortedList)
        }
}

fun byCount(sortedList: List<String> = listOf(), args: Array<String> = arrayOf()) {
    var skip = 1
    val mapList = mutableMapOf<String, Int>()
    for (element in sortedList) {
        skip -= 1
        if (skip != 0) continue
        val times = sortedList.count { it == element }
        mapList[element] = times
        skip = times
    }
    if (!args.contains("line")) {
        val sortedMap = mapList.toList().sortedBy { it.second }.toMap()
        for ((number, times) in sortedMap) {
            val percent = (times.toDouble() / sortedList.size.toDouble() * 100).toInt()
            if (OutputType != 1) {
                println("${number}: $times time(s), ${percent}%")
            } else File(fileOutputName).appendText("\n${number}: $times time(s), ${percent}%")
        }
    } else {
        val listCount = mapList
            .toList()
            .sortedWith(compareBy({ it.second}, { it.first }))
            .toMap()
        for ((key, value) in listCount) {
            val percent = (value.toDouble() / sortedList.size.toDouble() * 100).toInt()
            if (OutputType != 1) {
                println("$key: $value time(s), $percent%")
            } else File(fileOutputName).appendText("\n$key: $value time(s), $percent%")
        }
    }
}


/*
fun storting(scanner: Scanner) {
    val numberList: MutableList<Int> = mutableListOf()
    while (scanner.hasNext()) {
        val input = scanner.next()
        val num = input.toIntOrNull()
        if (num is Int) {
            numberList.add(num)
        }
    }
    val sortedList = numberList.sorted()
    println("Total numbers: ${numberList.size}.")
    println("Sorted data: ${sortedList.joinToString(" ")}" )

}
*/

/*
fun long(scanner: Scanner) {
    val numberList: MutableList<Int> = mutableListOf()
    while (scanner.hasNext()) {
        val input = scanner.next()
        val num = input.toIntOrNull()
        if (num is Int) {
            numberList.add(num)
        }
    }
    val greatest = numberList.maxOrNull()
    if (greatest != null) {
        val greatestTimes = numberList.count {it == greatest}
        println("Total numbers: ${numberList.size}.")
        println("The greatest number: $greatest ($greatestTimes times(s), ${((greatestTimes.toDouble() / numberList.size.toDouble())* 100.0).toInt()}%).")
    } else println("0 numbers.")
}
*/

/*
fun line(scanner: Scanner) {
    val stringList: MutableList<String> = mutableListOf()
    while (scanner.hasNext()) {
        val line = scanner.nextLine()
        stringList.add(line)
    }
    val longest = stringList.maxByOrNull { it.length }
    val timesLongest = stringList.count { it == longest }
    println("Total lines: ${stringList.size}.")
    println("The longest line:")
    println(longest)
    println("($timesLongest times(s), ${((timesLongest.toDouble() / stringList.size.toDouble())* 100.0).toInt()}%).")
}
*/
 /*
fun word(scanner: Scanner) {
    val numberList: MutableList<String> = mutableListOf()
    while (scanner.hasNext()) {
        val input = scanner.next()
            numberList.add(input)
    }
    val greatest = numberList.maxByOrNull { it.length }
    if (greatest != null) {
        val greatestTimes = numberList.count { it == greatest }

        println("Total words: ${numberList.size}.")
        println("The longest word: $greatest ($greatestTimes times(s), ${((greatestTimes.toDouble() / numberList.size.toDouble()) * 100.0).toInt()}%).")
    } else println("0 numbers.")

}
*/

