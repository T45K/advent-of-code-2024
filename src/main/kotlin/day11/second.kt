package day11

import kotlin.io.path.readLines
import resourcePath

private const val REPEAT_COUNT = 75

fun main() {
    val line = resourcePath.resolve("day11.txt").readLines()[0]
    val initial = line.split(" ").map { it.toLong() }

    val answer = initial.sumOf { calcLength(it, 0) }
    println(answer)
}

private val memo = mutableMapOf<Pair<Long, Int>, Long>()

private fun calcLength(value: Long, count: Int): Long {
    if (memo[value to count] != null) {
        return memo[value to count]!!
    }

    if (count == REPEAT_COUNT) {
        memo[value to count] = 1
        return 1
    }

    val length = when {
        value == 0L -> calcLength(1, count + 1)
        value.toString().length % 2 == 0 -> {
            val valueStr = value.toString()
            calcLength(valueStr.substring(0..<valueStr.length / 2).toLong(), count + 1) +
                calcLength(valueStr.substring(valueStr.length / 2..<valueStr.length).toLong(), count + 1)
        }

        else -> calcLength(value * 2024, count + 1)
    }

    memo[value to count] = length
    return length
}
