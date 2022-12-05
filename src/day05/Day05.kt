package day05

import readInput

class Command constructor(val quantity: Int, val source: Int, val destination: Int) {

}

fun main() {

    fun move1(piles: MutableList<ArrayDeque<Char>>, command: Command): MutableList<ArrayDeque<Char>> {
        val pileFrom = piles[command.source - 1]
        val pileTo = piles[command.destination - 1]
        val items = pileFrom.chunked(command.quantity).first()
        items.forEach { _ -> pileFrom.removeFirst() }
        items.forEach { item -> pileTo.addFirst(item) }
        return piles
    }

    fun move2(piles: MutableList<ArrayDeque<Char>>, command: Command): MutableList<ArrayDeque<Char>> {
        val pileFrom = piles[command.source - 1]
        val pileTo = piles[command.destination - 1]
        val items = pileFrom.chunked(command.quantity).first()
        items.forEach { _ -> pileFrom.removeFirst() }
        items.reversed().forEach { item -> pileTo.addFirst(item) }
        return piles
    }

    fun part1(input: List<String>): String {
        var crates = input.filter { row -> row.startsWith(" ") || row.startsWith("[") }.map { row -> row.replace("    ", "[?]").replace(" ", "").replace("[", "").replace("]", "").toCharArray() };
        val commands = input.filter { row -> row.startsWith("m") }

        var piles: MutableList<ArrayDeque<Char>> = ArrayList();

        crates = crates.subList(0, crates.size - 1)
        crates[0].forEach { _ -> piles.add(ArrayDeque()) }
        piles.forEachIndexed { index, pile -> crates.forEach { crate -> if ('?' != crate[index]) pile.addLast(crate[index]) } }


        val encodedCommands = commands.map { command -> command.split(" ") }.map { commandsItems -> Command(commandsItems[1].toInt(), commandsItems[3].toInt(), commandsItems[5].toInt()) }
        encodedCommands.forEach { command -> piles = move1(piles, command) }

        return piles.map { pile -> pile.first() }.joinToString("");
    }

    fun part2(input: List<String>): String {
        var crates = input.filter { row -> row.startsWith(" ") || row.startsWith("[") }.map { row -> row.replace("    ", "[?]").replace(" ", "").replace("[", "").replace("]", "").toCharArray() };
        val commands = input.filter { row -> row.startsWith("m") }

        var piles: MutableList<ArrayDeque<Char>> = ArrayList();

        crates = crates.subList(0, crates.size - 1)
        crates[0].forEach { _ -> piles.add(ArrayDeque()) }
        piles.forEachIndexed { index, pile -> crates.forEach { crate -> if ('?' != crate[index]) pile.addLast(crate[index]) } }


        val encodedCommands = commands.map { command -> command.split(" ") }.map { commandsItems -> Command(commandsItems[1].toInt(), commandsItems[3].toInt(), commandsItems[5].toInt()) }
        encodedCommands.forEach { command -> piles = move2(piles, command) }

        return piles.map { pile -> pile.first() }.joinToString("")
    }

    val testInput = readInput("files/Day05_test")
    println(part1(testInput))
    check(part1(testInput) == "CMZ")

    val input = readInput("files/Day05")
    println(part1(input))
    println(part2(input))
}
