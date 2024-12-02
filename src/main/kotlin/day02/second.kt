package day02

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day02.txt").readLines()
    val answer = lines.map { line -> line.split(" ").map { it.toInt() } }
        .count { list ->
            val candidates = listUpCandidates(list)
            candidates.any { it.isIncreasing() } ||
                candidates.any { it.isDecreasing() }
        }
    println(answer)
}

private fun listUpCandidates(list: List<Int>): List<List<Int>> = list.mapIndexed { index, _ -> list.removeAt(index) } + listOf(list)

private fun List<Int>.removeAt(index: Int): List<Int> = filterIndexed { idx, _ -> index != idx }

private fun List<Int>.isIncreasing(): Boolean = zipWithNext().all { it.first < it.second && (it.second - it.first <= 3) }
private fun List<Int>.isDecreasing(): Boolean = zipWithNext().all { it.first > it.second && (it.first - it.second <= 3) }
