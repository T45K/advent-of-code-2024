package day03

import kotlin.io.path.readLines
import resourcePath

fun main() {
    val regex = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)|don't|do".toRegex()
    val line = resourcePath.resolve("day03.txt").readLines().joinToString("")
    val answer = regex.findAll(line).fold(true to 0L) { (isEnabled, sum), matched ->
        when (matched.value) {
            "do" -> true to sum
            "don't" -> false to sum
            else -> {
                if (isEnabled) {
                    val groupValues = matched.groupValues
                    true to sum + groupValues[1].toLong() * groupValues[2].toLong()
                } else {
                    false to sum
                }
            }
        }
    }.second
    println(answer)
}
