package day03

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val regex = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)".toRegex()
    val answer = resourcePath.resolve("day03.txt").readLines().sumOf { line ->
        regex.findAll(line).sumOf {
            val groupValues = it.groupValues
            groupValues[1].toLong() * groupValues[2].toLong()
        }
    }
    println(answer)
}
