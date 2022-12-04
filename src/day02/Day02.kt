package day02

import readInput

enum class Symbol(val encode: String, val value: Int) {
    ROCK("A,X", 1),
    PAPER("B,Y", 2),
    SCISSOR("C,Z", 3);

    companion object {
        fun getSymbolByEncode(encode: String): Symbol? {
            return Symbol.values().firstOrNull { symbol: Symbol ->
                symbol.encode.split(",").any { encodeValue -> encodeValue == encode }
            }
        }

        fun getWinningOver(symbol: Symbol): Symbol {
            if (symbol == ROCK) return PAPER
            if (symbol == SCISSOR) return ROCK
            return SCISSOR
        }

        fun getLosingOver(symbol: Symbol): Symbol {
            if (symbol == ROCK) return SCISSOR
            if (symbol == SCISSOR) return PAPER
            return ROCK
        }
    }
}

fun main() {

    fun calculateAmount(otherSymbol: Symbol?, mySymbol: Symbol?): Int? {

        val amount = mySymbol?.value

        if (otherSymbol == Symbol.SCISSOR && mySymbol == Symbol.ROCK ||
                otherSymbol == Symbol.ROCK && mySymbol == Symbol.PAPER ||
                otherSymbol == Symbol.PAPER && mySymbol == Symbol.SCISSOR) {
            return amount?.plus(6)
        }

        if (otherSymbol == mySymbol) {
            return amount?.plus(3)
        }

        return amount;
    }

    fun calcResult1(symbols: List<String>): Int? {
        val otherSymbol = Symbol.getSymbolByEncode(symbols[0])
        val mySymbol = Symbol.getSymbolByEncode(symbols[1])

        return calculateAmount(otherSymbol, mySymbol)
    }

    fun calcResult2(symbols: List<String>): Int? {
        val otherSymbol = Symbol.getSymbolByEncode(symbols[0])
        if ("Z" == symbols[1]) {
            return calculateAmount(otherSymbol, Symbol.getWinningOver(otherSymbol!!))
        }
        if ("X" == symbols[1]) {
            return calculateAmount(otherSymbol, Symbol.getLosingOver(otherSymbol!!));
        }
        return calculateAmount(otherSymbol, otherSymbol);
    }

    fun part1(input: List<String>): Int {
        return input.map { row -> row.split(" ") }.map { symbols -> calcResult1(symbols) }.reduce { x, y -> x?.plus(y!!) }!!
    }

    fun part2(input: List<String>): Int {
        return input.map { row -> row.split(" ") }.map { symbols -> calcResult2(symbols) }.reduce { x, y -> x?.plus(y!!) }!!
    }

    val testInput = readInput("files/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("files/Day02")
    println(part1(input))
    println(part2(input))
}
