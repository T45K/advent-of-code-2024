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

    val answer = (0..<lines.size).sumOf { i ->
        (0..<lines[0].length).count { j ->
            val isVisited = Array(lines.size) { Array(lines[0].length) { 0 } }
            var moveIndex = 0
            var currentPoint = initialPoint
            if (lines[i][j] != '.') {
                return@count false
            }
            try {
                while (true) {
                    if ((isVisited[currentPoint.first][currentPoint.second] and (1 shl moveIndex)) > 0) {
                        return@count true
                    }
                    isVisited[currentPoint.first][currentPoint.second] = isVisited[currentPoint.first][currentPoint.second] or (1 shl moveIndex)
                    val nextPoint = (currentPoint.first + moveX[moveIndex]) to (currentPoint.second + moveY[moveIndex])
                    if (lines[nextPoint.first][nextPoint.second] == '#' || nextPoint.first == i && nextPoint.second == j) {
                        moveIndex = (moveIndex + 1) % 4
                    } else {
                        currentPoint = nextPoint
                    }
                }
                false
            } catch (_: Exception) {
                false
            }
        }
    }

    println(answer)
}
