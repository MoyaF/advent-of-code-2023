package Day01

import println
import readInput

fun main() {
    val numbers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun part1(input: List<String>): Int {
        return input.sumOf { word ->
            val charArray = word.toCharArray()
            val firstDigit = charArray.find { it.isDigit() } ?: error("bad input")
            val lastDigit = charArray.findLast { it.isDigit() } ?: error("bad input")
            "$firstDigit$lastDigit".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val updatedList = input.map { word ->
            if (numbers.keys.none { word.contains(it) }) return@map word
            var updatedString = ""
            for (startIndex in word.indices) {
                updatedString += word[startIndex]
                for (endIndex in startIndex .. (word.length)) {
                    val substring = word.substring(startIndex..<endIndex)
                    val foundNumber = numbers[substring] ?: continue
                    updatedString += "$foundNumber"
                }
            }
            updatedString
        }
        return part1(updatedList)

    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
