package day08

import resourcePath
import kotlin.io.path.readLines

fun main() {
    val lines = resourcePath.resolve("day08.txt").readLines()

    val h = lines.size
    val w = lines[0].length

    val pointsBySymbol = (0..<h).flatMap { i -> (0..<w).map { j -> i to j } }
        .filter { (i, j) -> lines[i][j] != '.' }
        .groupBy { (i, j) -> lines[i][j] }

    val answer = pointsBySymbol.values.flatMap { points ->
        points.flatMap { basePoint ->
            points.filter { basePoint != it }.mapNotNull { targetPoint ->
                val antiNodeX = 2 * targetPoint.first - basePoint.first
                val antiNodeY = 2 * targetPoint.second - basePoint.second
                if (0 <= antiNodeX && antiNodeX < h &&
                    0 <= antiNodeY && antiNodeY < w
                ) {
                    antiNodeX to antiNodeY
                } else null
            }
        }
    }.distinct().count()

    println(answer)
}
