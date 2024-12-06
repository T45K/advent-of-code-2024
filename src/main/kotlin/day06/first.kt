package day06

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day06.txt").readLines()

    val initialPoint = (0..<lines.size).flatMap { i ->
        (0..<lines[i].length).map { j -> i to j }
    }.first { (i, j) -> lines[i][j] == '^' }

    val moveX = arrayOf(-1, 0, 1, 0)
    val moveY = arrayOf(0, 1, 0, -1)

    val isVisited = Array(lines.size) { Array(lines[0].length) { false } }
    var moveIndex = 0
    var currentPoint = initialPoint
    try {
        while (true) {
            isVisited[currentPoint.first][currentPoint.second] = true
            val nextPoint = (currentPoint.first + moveX[moveIndex]) to (currentPoint.second + moveY[moveIndex])
            if (lines[nextPoint.first][nextPoint.second] != '#') {
                currentPoint = nextPoint
            } else {
                moveIndex = (moveIndex + 1) % 4
            }
        }
    } catch (_: Exception) {
    }

    val answer = isVisited.sumOf { array -> array.count { it } }
    println(answer)
}
