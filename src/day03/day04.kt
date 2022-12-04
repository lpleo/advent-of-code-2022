package day03

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return input
                .asSequence()
                .map { row -> row.split(",") }
                .map { assignments -> assignments.map { assignment -> assignment.split("-") } }
                .map { assignments -> assignments.map { assignment -> arrayOf(assignment[0].toInt(), assignment[1].toInt()) } }
                .filter { assignments ->
                    (assignments[0][0] <= assignments[1][0] && assignments[0][1] >= assignments[1][1]) ||
                            (assignments[1][0] <= assignments[0][0] && assignments[1][1] >= assignments[0][1])
                }.count();
    }

    fun part2(input: List<String>): Int {
        return input
                .asSequence()
                .map { row -> row.split(",") }
                .map { assignments -> assignments.map { assignment -> assignment.split("-") } }
                .map { assignments -> assignments.map { assignment -> arrayOf(assignment[0].toInt(), assignment[1].toInt()) } }
                .filter { assignments ->
                    (assignments[1][0] <= assignments[0][0] && assignments[0][0] <= assignments[1][1]) ||
                            (assignments[1][0] <= assignments[0][1] && assignments[0][1] <= assignments[1][1]) ||
                            (assignments[0][0] <= assignments[1][0] && assignments[1][0] <= assignments[0][1]) ||
                            (assignments[0][0] <= assignments[1][1] && assignments[1][1] <= assignments[0][1])
                }.count();
    }

    val testInput = readInput("files/Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("files/Day04")
    println(part1(input))
    println(part2(input))
}
