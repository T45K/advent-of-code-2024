package day04

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day04.txt").readLines()

    val row = lines.size
    val col = lines[0].length

    var answer = 0
    for (i in 0..<row) {
        for (j in 0..<col) {
            if (i >= 3 && lines[i][j] == 'X' && lines[i - 1][j] == 'M' && lines[i - 2][j] == 'A' && lines[i - 3][j] == 'S') answer++
            if (i < row - 3 && lines[i][j] == 'X' && lines[i + 1][j] == 'M' && lines[i + 2][j] == 'A' && lines[i + 3][j] == 'S') answer++
            if (j >= 3 && lines[i][j] == 'X' && lines[i][j - 1] == 'M' && lines[i][j - 2] == 'A' && lines[i][j - 3] == 'S') answer++
            if (j < col - 3 && lines[i][j] == 'X' && lines[i][j + 1] == 'M' && lines[i][j + 2] == 'A' && lines[i][j + 3] == 'S') answer++

            if (i >= 3 && j >= 3 && lines[i][j] == 'X' && lines[i - 1][j - 1] == 'M' && lines[i - 2][j - 2] == 'A' && lines[i - 3][j - 3] == 'S') answer++
            if (i < row - 3 && j >= 3 && lines[i][j] == 'X' && lines[i + 1][j - 1] == 'M' && lines[i + 2][j - 2] == 'A' && lines[i + 3][j - 3] == 'S') answer++
            if (i >= 3 && j < col - 3 && lines[i][j] == 'X' && lines[i - 1][j + 1] == 'M' && lines[i - 2][j + 2] == 'A' && lines[i - 3][j + 3] == 'S') answer++
            if (i < row - 3 && j < col - 3 && lines[i][j] == 'X' && lines[i + 1][j + 1] == 'M' && lines[i + 2][j + 2] == 'A' && lines[i + 3][j + 3] == 'S') answer++
        }
    }
    println(answer)
}
