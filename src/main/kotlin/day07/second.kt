package day07

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val lines = resourcePath.resolve("day07.txt").readLines()
    val answer = lines.sumOf { line ->
        val split = line.split(": ")
        val testValue = split[0].toLong()
        val numbers = split[1].split(" ").map { it.toLong() }
        if (calculate(numbers, 1, numbers[0], testValue)) {
            testValue
        } else {
            0
        }
    }
    println(answer)
}

private fun calculate(list: List<Long>, index: Int, total: Long, testValue: Long): Boolean =
    if (index == list.size) {
        total == testValue
    } else {
        calculate(list, index + 1, total + list[index], testValue) ||
            calculate(list, index + 1, total * list[index], testValue) ||
            calculate(list, index + 1, (total.toString() + list[index].toString()).toLong(), testValue)
    }
