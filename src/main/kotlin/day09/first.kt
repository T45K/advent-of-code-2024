package day09

import resourcePath
import kotlin.io.path.readLines

fun main() {
    val line = resourcePath.resolve("day09.txt").readLines()[0]

    val array = line.mapIndexed { index, ch ->
        if (index % 2 == 0) Array(ch.digitToInt()) { index / 2 }
        else Array<Int?>(ch.digitToInt()) { null }
    }.toTypedArray().flatten().toTypedArray()

    var head = 0
    var last = array.size - 1
    while (head < last) {
        if (array[head] != null) {
            head++
            continue
        }
        if (array[last] == null) {
            last--
            continue
        }
        array[head] = array[last]
        head++
        array[last] = null
        last--
    }
    val answer = array.filterNotNull()
        .mapIndexed { index, i -> (index * i).toLong() }
        .sum()
    println(answer)
}
