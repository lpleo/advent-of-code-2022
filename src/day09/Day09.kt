package day09

import readInput

private const val FILES_DAY_TEST = "files/Day09_test"
private const val FILES_DAY = "files/Day09"

class Point(var x: Int, var y: Int) {

    fun go(direction: Char) = run {
        if (direction == 'R') x += 1;
        if (direction == 'L') x -= 1;
        if (direction == 'U') y += 1
        if (direction == 'D') y -= 1;
    }

    fun follow(head: Point) = run {
        if (kotlin.math.abs(this.x - head.x) <= 1 && kotlin.math.abs(this.y - head.y) <= 1) {
            return@run Point(this.x, this.y)
            //do nothing
        }
        if (head.x == this.x) {
            if (head.y > this.y) this.y += 1
            else this.y -= 1

            return@run Point(this.x, this.y)
        }
        if (head.y == this.y) {
            if (head.x > this.x) this.x += 1
            else this.x -= 1

            return@run Point(this.x, this.y)
        }

        if (head.x - this.x > 0 && head.y - this.y > 0) {
            this.x += 1
            this.y += 1
        }

        if (head.x - this.x < 0 && head.y - this.y > 0) {
            this.x -= 1
            this.y += 1
        }

        if (head.x - this.x > 0 && head.y - this.y < 0) {
            this.x += 1
            this.y -= 1
        }

        if (head.x - this.x < 0 && head.y - this.y < 0) {
            this.x -= 1
            this.y -= 1
        }

        return@run Point(this.x, this.y);
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Point) other.x == this.x && other.y == this.y else false;
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

}

fun main() {

    fun part1(input: List<String>): Int {

        val head = Point(0, 0)
        val tail = Point(0, 0)
        val visitedTailPlaces = ArrayList<Point>()

        input.map { row -> row.split(" ") }.forEach { command ->
            val direction = command[0].toCharArray()[0]
            val amount = command[1].toInt()

            for (i in 0 until amount) {
                head.go(direction)
                val visitedPoint = tail.follow(head)
                if (!visitedTailPlaces.contains(visitedPoint)) visitedTailPlaces.add(visitedPoint)
            }
        }
        return visitedTailPlaces.count()
    }

    fun part2(input: List<String>): Int {
        val head = Point(0, 0);
        val points = ArrayList<Point>()
        points.add(head)

        val visitedTailPlaces = ArrayList<Point>()
        var nodeCounter = 0;

        input.map { row -> row.split(" ") }.forEach { command ->
            val direction = command[0].toCharArray()[0]
            val amount = command[1].toInt()

            for (i in 0 until amount) {
                head.go(direction)

                if (nodeCounter < 9) {
                    points.add(Point(0, 0))
                    nodeCounter++
                }

                for (pointNumber in 1 until points.size) {
                    val visitedPoint = points[pointNumber].follow(points[pointNumber - 1])
                    if (pointNumber == points.size - 1 && !visitedTailPlaces.contains(visitedPoint)) {
                        visitedTailPlaces.add(visitedPoint)
                    }
                }
            }
        }

        return visitedTailPlaces.count()
    }

    println(part1(readInput(FILES_DAY_TEST)))
    check(part1(readInput(FILES_DAY_TEST)) == 13)
    println(part2(readInput(FILES_DAY_TEST)))
    check(part2(readInput(FILES_DAY_TEST)) == 1)
    println(part2(listOf("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20")))
    check(part2(listOf("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20")) == 36)

    val input = readInput(FILES_DAY)
    println(part1(input))
    println(part2(input))
}
