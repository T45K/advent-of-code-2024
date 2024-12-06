package day05

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day05.txt").readLines()
    val latters = lines.filter { it.contains("|") }
        .map { line -> line.split("|").let { (left, right) -> left.toInt() to right.toInt() } }
        .groupBy({ it.first }) { it.second }
        .mapValues { (_, list) -> list.toSet() }

    val answer = lines.filter { it.contains(",") }
        .map { line -> line.split(",").map { it.toInt() } }
        .filter { line ->
            line.fold(emptySet<Int>()) { formers, num ->
                if ((latters[num] ?: emptyList()).any { it in formers }) {
                    return@filter true
                }
                formers + num
            }
            false
        }
        .map { line ->
            val counts = line.associate { value ->
                value to line.count { it in (latters[value] ?: emptySet()) }
            }
            line.sortedBy { counts[it]!! }
        }
        .sumOf { it[it.size / 2] }
    println(answer)
}
