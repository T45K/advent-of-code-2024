package day01

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day01.txt").readLines()
    val (a, b) = lines.map { line -> line.split("   ").let { it[0].toLong() to it[1].toLong() } }.unzip()
    val bCounts = b.groupingBy { it }.eachCount()
    val answer = a.sumOf { it * (bCounts[it] ?: 0) }
    println(answer)
}
