package day11

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val line = resourcePath.resolve("day11.txt").readLines()[0]
    val initial = line.split(" ").map { it.toLong() }

    val answer = (0..<25).fold(initial) { prev, _ -> prev.flatMap { convert(it) } }.size
    println(answer)
}

private fun convert(value: Long): List<Long> =
    when {
        value == 0L -> listOf(1)
        value.toString().length % 2 == 0 -> {
            val valueStr = value.toString()
            listOf(
                valueStr.substring(0..<valueStr.length / 2).toLong(),
                valueStr.substring(valueStr.length / 2..<valueStr.length).toLong(),
            )
        }

        else -> listOf(value * 2024)
    }
