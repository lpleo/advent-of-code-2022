package day04

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return input
                .map { rucksack -> arrayOf(rucksack.substring(0, rucksack.length / 2), rucksack.substring((rucksack.length / 2), rucksack.length)) }
                .map { compartments ->
                    compartments[0].toCharArray().first { character ->
                        compartments[1].toCharArray().contains(character)
                    }
                }
                .sumOf { identifier -> if (identifier.isUpperCase()) identifier.code - 'A'.code + 27 else identifier.code - 'a'.code + 1 }
    }

    fun part2(input: List<String>): Int {
        return input
                .chunked(3)
                .map { rucksacks ->
                    rucksacks[0].toCharArray().first { charachter ->
                        rucksacks[1].toCharArray().firstOrNull { innerCharacter ->
                            rucksacks[2].toCharArray().contains(charachter) && innerCharacter == charachter
                        } != null
                    }
                }.sumOf { identifier -> if (identifier.isUpperCase()) identifier.code - 'A'.code + 27 else identifier.code - 'a'.code + 1 }
    }

    val testInput = readInput("files/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("files/Day03")
    println(part1(input))
    println(part2(input))
}
