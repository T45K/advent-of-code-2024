package day12

import ignoreException
import ignoreExceptionOrNull
import kotlin.io.path.readLines
import resourcePath

private val xMove = listOf(0, 1, 0, -1)
private val yMove = listOf(1, 0, -1, 0)

fun main() {
    val lines = resourcePath.resolve("day12.txt").readLines()

    val h = lines.size
    val w = lines[0].length

    val table = preprocess(lines, h, w)

    val areaByRegion = table.flatten()
        .groupingBy { it }.eachCount()

    val perimeterByRegion = (0..<h).flatMap { i -> (0..<w).map { j -> i to j } }
        .groupBy({ (i, j) -> table[i][j] }) { (i, j) ->
            xMove.zip(yMove).sumOf { (x, y) ->
                ignoreExceptionOrNull {
                    if (table[i][j] == table[i + x][j + y]) {
                        0L
                    } else {
                        1L
                    }
                } ?: 1L
            }
        }.mapValues { (_, list) -> list.sum() }

    val answer = areaByRegion.map { (region, area) ->
        area * perimeterByRegion[region]!!
    }.sum()
    println(answer)
}

private fun preprocess(lines: List<String>, h: Int, w: Int): Array<Array<Int>> {
    val table = Array(h) { Array(w) { 0 } }
    table[0][0] = 1
    fillIn(lines, table, 0, 0)

    var tmp = 2
    (0..<h).forEach { i ->
        (0..<w).forEach { j ->
            if (table[i][j] == 0) {
                table[i][j] = tmp++
                fillIn(lines, table, i, j)
            }
        }
    }

    return table
}

private fun fillIn(lines: List<String>, table: Array<Array<Int>>, i: Int, j: Int) {
    xMove.zip(yMove).forEach { (x, y) ->
        ignoreException {
            if (lines[i][j] == lines[i + x][j + y] && table[i + x][j + y] == 0) {
                table[i + x][j + y] = table[i][j]
                fillIn(lines, table, i + x, j + y)
            }
        }
    }
}
