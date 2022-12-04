package day01

import readInput

fun main() {

    fun getMaxCaloriesList(input: List<String>): MutableList<Int> {
        val maxCaloriesList: MutableList<Int> = mutableListOf(0);
        input.forEach {
            if (it.isNotEmpty()) {
                maxCaloriesList[maxCaloriesList.lastIndex] = maxCaloriesList[maxCaloriesList.lastIndex] + Integer.parseInt(it);
            } else {
                maxCaloriesList.add(0);
            }
        }
        return maxCaloriesList
    }

    fun part1(input: List<String>): Int {
        val maxCaloriesList: MutableList<Int> = getMaxCaloriesList(input)
        return maxCaloriesList.max().or(0)
    }

    fun part2(input: List<String>): Int {
        val maxCaloriesList = getMaxCaloriesList(input)
        val maxCaloriesOrderedList = maxCaloriesList.sortedDescending()
        return maxCaloriesOrderedList[0] + maxCaloriesOrderedList[1] + maxCaloriesOrderedList[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("files/Day01_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 17)

    val input = readInput("files/Day01")
    println(part1(input))
    println(part2(input))
}
