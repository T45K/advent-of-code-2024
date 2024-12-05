package day05

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day05.txt").readLines()
    val latters = lines.filter { it.contains("|") }
        .map { line -> line.split("|").let { (left, right) -> left.toInt() to right.toInt() } }
        .groupBy({ it.first }) { it.second }

    val answer = lines.filter { it.contains(",") }
        .map { line -> line.split(",").map { it.toInt() } }
        .filter { line ->
            line.fold(emptySet<Int>()) { formers, num ->
                if ((latters[num] ?: emptyList()).any { it in formers }) {
                    return@filter false
                }
                formers + num
            }
            true
        }
        .sumOf { it[it.size / 2] }
    println(answer)
}
