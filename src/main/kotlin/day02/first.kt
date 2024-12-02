package day02

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day02.txt").readLines()
    val answer = lines.map { line -> line.split(" ").map { it.toInt() } }
        .count { it.isIncreasing() || it.isDecreasing() }
    println(answer)
}

private fun List<Int>.isIncreasing(): Boolean = zipWithNext().all { it.first < it.second && (it.second - it.first <= 3) }
private fun List<Int>.isDecreasing(): Boolean = zipWithNext().all { it.first > it.second && (it.first - it.second <= 3) }