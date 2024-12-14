package day10

import kotlin.io.path.readLines
import resourcePath

typealias Point = Pair<Int, Int>

val Point.x: Int
    get() = first

val Point.y: Int
    get() = second

fun main() {
    val lines = resourcePath.resolve("day10.txt").readLines()

    val h = lines.size
    val w = lines[0].length
    val pointsByNumber = (0..<h).flatMap { i -> (0..<w).map { j -> i to j } }
        .groupBy { (i, j) -> lines[i][j].digitToInt() }

    val topsByPoints = pointsByNumber[9]!!.associateWith { mutableSetOf(it) }.toMutableMap()

    val xMove = arrayOf(1, 0, -1, 0)
    val yMove = arrayOf(0, 1, 0, -1)

    for (height in 9 downTo 0) {
        for (point in pointsByNumber[height]!!) {
            val tops = topsByPoints[point] ?: emptySet()
            for (moveIndex in 0..<4) {
                val nextPoint = (point.x + xMove[moveIndex]) to (point.y + yMove[moveIndex])
                ignoreException {
                    if (lines[nextPoint.x][nextPoint.y].digitToInt() == height - 1) {
                        topsByPoints.computeIfAbsent(nextPoint) { _ -> mutableSetOf() }
                            .addAll(tops)
                    }
                }
            }
        }
    }

    val answer = pointsByNumber[0]!!.flatMap { topsByPoints[it] ?: emptySet() }.count()
    println(answer)
}

private fun ignoreException(block: () -> Unit) {
    try {
        block()
    } catch (_: Exception) {
    }
}
