package day04

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day04.txt").readLines()

    val row = lines.size
    val col = lines[0].length

    var answer = 0
    for (i in 1..<row - 1) {
        for (j in 1..<col - 1) {
            if (lines[i][j] == 'A') {
                if (
                    lines[i - 1][j - 1] == 'M' && lines[i + 1][j + 1] == 'S' && lines[i - 1][j + 1] == 'M' && lines[i + 1][j - 1] == 'S' ||
                    lines[i - 1][j - 1] == 'S' && lines[i + 1][j + 1] == 'M' && lines[i - 1][j + 1] == 'M' && lines[i + 1][j - 1] == 'S' ||
                    lines[i - 1][j - 1] == 'M' && lines[i + 1][j + 1] == 'S' && lines[i - 1][j + 1] == 'S' && lines[i + 1][j - 1] == 'M' ||
                    lines[i - 1][j - 1] == 'S' && lines[i + 1][j + 1] == 'M' && lines[i - 1][j + 1] == 'S' && lines[i + 1][j - 1] == 'M'
                ) {
                    answer++
                }
            }
        }
    }
    println(answer)
}
