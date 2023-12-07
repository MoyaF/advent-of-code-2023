package Day02

import println
import readInput

fun main() {

    fun maxByColor(color: Color) = when (color) {
        Color.RED -> 12
        Color.GREEN -> 13
        Color.BLUE -> 14
    }

    fun mapToGame(game: String): Game {
        val splittedGame = game.split(":")
        val play = splittedGame.last()
            .split(";")
            .map { cubeSubset ->
                Play(
                    cubeSubset.split(",")
                        .asSequence()
                        .map { it.removePrefix(" ") }
                        .map { it.split(" ") }
                        .map { Cube(it.first().toInt(), Color.valueOf(it.last().uppercase())) }
                        .groupBy { it.color }
                        .map { (color, cubes) -> Cube(cubes.sumOf { it.ammount }, color) }
                        .toList()
                )
            }

        return Game(
            id = splittedGame.first().split(" ").last().toInt(),
            plays = play
        )
    }

    fun part1(input: List<String>): Int {
        return input.map { mapToGame(it) }
            .filter { (_, plays) -> plays.flatMap { it.cubes }.none { it.ammount > maxByColor(it.color) } }
            .sumOf { it.id }
    }


    fun part2(input: List<String>): Int {
        return input.map { mapToGame(it) }
            .map { (_, plays) -> plays.flatMap { it.cubes }.groupBy { it.color }.mapValues { (_, colors) -> colors.maxBy { it.ammount } } }
            .map { it.values.map { it.ammount }.reduce(Int::times) }
            .sumOf { it }
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

data class Game(
    val id: Int,
    val plays: List<Play>,
)

data class Play(
    val cubes: List<Cube>,
)

data class Cube(
    val ammount: Int,
    val color: Color,
)

enum class Color {
    RED, BLUE, GREEN
}