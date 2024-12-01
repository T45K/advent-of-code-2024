package day01

import kotlin.io.path.readLines
import kotlin.math.abs
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day01.txt").readLines()
    val (a, b) = lines.map { line -> line.split("   ").let { it[0].toLong() to it[1].toLong() } }.unzip()
    val answer = a.sorted().zip(b.sorted()).sumOf { abs(it.first - it.second) }
    println(answer)
}
