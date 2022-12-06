package day06

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val string = input[0]
        for (i in 0..string.length) {
            val reduce = string.substring(i, i + 4).map { character -> string.substring(i, i + 4).count { internalChar -> internalChar == character } == 1 }.reduce { a, b -> a && b }
            if (reduce) return i + 4;
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val string = input[0]
        for (i in 0..string.length) {
            val reduce = string.substring(i, i + 14).map { character -> string.substring(i, i + 14).count { internalChar -> internalChar == character } == 1 }.reduce { a, b -> a && b }
            if (reduce) return i + 14;
        }
        return 0
    }

    println(part1(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")))
    check(part1(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")) == 7)
    println(part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")))
    check(part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) == 5)
    println(part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")))
    check(part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")) == 6)
    check(part1(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == 10)
    check(part1(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == 11)

    val input = readInput("files/Day06")
    println(part1(input))
    println(part2(input))
}
