package day11

import readInput
import kotlin.math.floor

private const val FILES_DAY_TEST = "files/Day11_test"
private const val FILES_DAY = "files/Day11"

abstract class Monkey(var worryLevels: ArrayDeque<Double>) {

    var itemInspected = 0;

    abstract fun operation(worryLevel: Double): Double
    abstract fun nextMonkey(worryLevel: Double): Int
    fun countItem() {
        itemInspected++
    }
}

fun main() {

    fun part1(input: List<String>): Any {
        val monkeyList = if (input.size < 40) {
            listOf(TestMonkey0(), TestMonkey1(), TestMonkey2(), TestMonkey3());
        } else {
            listOf(Monkey0(), Monkey1(), Monkey2(), Monkey3(), Monkey4(), Monkey5(), Monkey6(), Monkey7());
        }

        for (i in 0 until 20) {
            println("\n round $i")
            monkeyList.forEach { monkey ->
                monkey.worryLevels.forEach { worryLevel ->
                    print("a:$worryLevel ")
                    val newWorryLevel = floor(monkey.operation(worryLevel).toDouble() / 3.0)
                    print("n:$newWorryLevel ")
                    monkeyList[monkey.nextMonkey(newWorryLevel)].worryLevels.addLast(newWorryLevel)
                    println("m:$monkey mto:${monkeyList[monkey.nextMonkey(newWorryLevel)]}")
                    monkey.countItem()
                }
                println()
                monkey.worryLevels = ArrayDeque();
            }

            monkeyList.forEach { monkey -> println("$monkey -> ${monkey.worryLevels}") }
        }

        println()
        monkeyList.forEach { monkey -> println("$monkey -> ${monkey.itemInspected}") }

        return monkeyList.sortedBy { monkey -> monkey.itemInspected }.reversed().subList(0, 2)
            .map { monkey -> monkey.itemInspected }.reduce { a, b -> a * b }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    println(part1(readInput(FILES_DAY_TEST)))
    check(part1(readInput(FILES_DAY_TEST)) == 10605)
    println(part2(readInput(FILES_DAY_TEST)))
    check(part2(readInput(FILES_DAY_TEST)) == 0)

    val input = readInput(FILES_DAY)
    println(part1(input))
    println(part2(input))
}

class TestMonkey0() : Monkey(ArrayDeque(mutableListOf(79.0, 98.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel * 19
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 23.0 == 0.0) {
            return 2
        }
        return 3
    }
}

class TestMonkey1() : Monkey(ArrayDeque(mutableListOf(54.0, 65.0, 75.0, 74.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 6
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 19.0 == 0.0) {
            return 2
        }
        return 0
    }
}

class TestMonkey2() : Monkey(ArrayDeque(mutableListOf(79.0, 60.0, 97.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel * worryLevel
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 13.0 == 0.0) {
            return 1
        }
        return 3
    }
}

class TestMonkey3() : Monkey(ArrayDeque(mutableListOf(74.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 3
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 17.0 == 0.0) {
            return 0
        }
        return 1
    }
}

class Monkey0 : Monkey(ArrayDeque(mutableListOf(54.0, 98.0, 50.0, 94.0, 69.0, 62.0, 53.0, 85.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel * 13
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 3.0 == 0.0) {
            return 2
        }
        return 1
    }
}

class Monkey1 : Monkey(ArrayDeque(mutableListOf(71.0, 55.0, 82.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 2
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 13.0 == 0.0) {
            return 7
        }
        return 2
    }
}

class Monkey2 : Monkey(ArrayDeque(mutableListOf(77.0, 73.0, 86.0, 72.0, 87.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 8
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 19.0 == 0.0) {
            return 4
        }
        return 7
    }
}

class Monkey3 : Monkey(ArrayDeque(mutableListOf(97.0, 91.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 1
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 17.0 == 0.0) {
            return 6
        }
        return 5
    }
}

class Monkey4 : Monkey(ArrayDeque(mutableListOf(78.0, 97.0, 51.0, 85.0, 66.0, 63.0, 62.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel * 17
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 5.0 == 0.0) {
            return 6
        }
        return 3
    }
}

class Monkey5 : Monkey(ArrayDeque(mutableListOf(88.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 3
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 7.0 == 0.0) {
            return 1
        }
        return 0
    }
}

class Monkey6 : Monkey(ArrayDeque(mutableListOf(87.0, 57.0, 63.0, 86.0, 87.0, 53.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel * worryLevel
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 11.0 == 0.0) {
            return 5
        }
        return 0
    }
}

class Monkey7 : Monkey(ArrayDeque(mutableListOf(73.0, 59.0, 82.0, 65.0))) {
    override fun operation(worryLevel: Double): Double {
        return worryLevel + 6
    }

    override fun nextMonkey(worryLevel: Double): Int {
        if (worryLevel % 2.0 == 0.0) {
            return 4
        }
        return 3
    }
}
