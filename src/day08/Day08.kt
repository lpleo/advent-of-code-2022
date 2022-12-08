package day08

import readInput
import kotlin.math.max

private const val FILES_DAY_08_TEST = "files/Day08_test"
private const val FILES_DAY_08 = "files/Day08"

fun main() {

    fun part1(input: List<String>): Int {
        val wood = createWood(input)
        var counter = 0;
        for (i in wood.indices) {
            for (j in wood[i].indices) {
                if (!existHigherTreeBefore(i, j, wood[i][j], wood) ||
                        !existHigherTreeAfter(i, j, wood[i][j], wood) ||
                        !existHigherTreeUp(i, j, wood[i][j], wood) ||
                        !existHigherTreeDown(i, j, wood[i][j], wood)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    fun part2(input: List<String>): Int {
        val wood = createWood(input)
        var maxScenicIndex = 0;
        for (i in wood.indices) {
            for (j in wood[i].indices) {
                val scenicIndex = scenicIndexBefore(i, j, wood) * scenicIndexAfter(i, j, wood) * scenicIndexUp(i, j, wood) * scenicIndexDown(i, j, wood)
                if (scenicIndex > maxScenicIndex) {
                    maxScenicIndex = scenicIndex
                }
            }
        }
        return maxScenicIndex
    }

    println(part1(readInput(FILES_DAY_08_TEST)))
    check(part1(readInput(FILES_DAY_08_TEST)) == 21)
    println(part2(readInput(FILES_DAY_08_TEST)))
    check(part2(readInput(FILES_DAY_08_TEST)) == 8)

    val input = readInput(FILES_DAY_08)
    println(part1(input))
    println(part2(input))
}

fun createWood(input: List<String>): Array<IntArray> {
    val wood = Array(input.size) { IntArray(input[0].length) }
    var rowPos = 0;
    var columnPos = 0
    input.forEach { row ->
        row.forEach { tree ->
            wood[columnPos][rowPos] = (tree + "").toInt()
            rowPos++;
        }
        rowPos = 0;
        columnPos += 1;
    }
    return wood
}

fun existHigherTreeBefore(col: Int, row: Int, treeHeight: Int, wood: Array<IntArray>): Boolean {
    for (rowPos in 0 until row) {
        if (wood[col][rowPos] >= treeHeight) {
            return true;
        }
    }
    return false;
}

fun existHigherTreeAfter(col: Int, row: Int, treeHeight: Int, wood: Array<IntArray>): Boolean {

    for (rowPos in row until wood[0].size) {
        if (rowPos != row && wood[col][rowPos] >= treeHeight) {
            return true;
        }
    }
    return false
}

fun existHigherTreeUp(col: Int, row: Int, treeHeight: Int, wood: Array<IntArray>): Boolean {
    for (columnPos in 0 until col) {
        if (wood[columnPos][row] >= treeHeight) {
            return true;
        }
    }
    return false;
}

fun existHigherTreeDown(col: Int, row: Int, treeHeight: Int, wood: Array<IntArray>): Boolean {
    for (columnPos in col until wood.size) {
        if (columnPos != col && wood[columnPos][row] >= treeHeight) {
            return true;
        }
    }
    return false;
}

fun scenicIndexBefore(col: Int, row: Int, wood: Array<IntArray>): Int {
    var counter = 0
    for (rowPos in (row - 1) downTo 0) {
        if (wood[col][rowPos] < wood[col][row]) {
            counter++
        } else {
            counter++
            break
        }
    }
    return counter
}

fun scenicIndexAfter(col: Int, row: Int, wood: Array<IntArray>): Int {
    var counter = 0
    for (rowPos in row + 1 until wood[0].size) {
        if (wood[col][rowPos] < wood[col][row]) {
            counter++
        } else {
            counter++
            break
        }
    }
    return counter
}

fun scenicIndexUp(col: Int, row: Int, wood: Array<IntArray>): Int {
    var counter = 0
    for (columnPos in (col - 1) downTo 0) {
        if (wood[columnPos][row] < wood[col][row]) {
            counter++
        } else {
            counter++
            break
        }
    }
    return counter
}

fun scenicIndexDown(col: Int, row: Int, wood: Array<IntArray>): Int {
    var counter = 0
    for (columnPos in col + 1 until wood.size) {
        if (wood[columnPos][row] < wood[col][row]) {
            counter++
        } else {
            counter++
            break
        }
    }
    return counter
}
