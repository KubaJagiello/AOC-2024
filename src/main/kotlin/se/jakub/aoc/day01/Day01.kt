package se.jakub.aoc.day01

import kotlin.math.abs
import se.jakub.aoc.Day
import se.jakub.aoc.extensions.*

class Day01 : Day {

  override fun part1(lines: List<String>): Any {
    val (listA, listB) = parseToTwoLists(lines).let { it.first.sorted() to it.second.sorted() }

    return listA.zip(listB).sumOf { abs(it.first - it.second) }
  }

  override fun part2(lines: List<String>): Any {
    val (listA, listB) = parseToTwoLists(lines)
    val bNumberCount = listB.groupingBy { it }.eachCount()

    return listA.sumOf { it * (bNumberCount[it] ?: 0) }
  }

  private fun parseToTwoLists(lines: List<String>): Pair<List<Int>, List<Int>> =
      lines.map { line -> line.splitBySpaces().let { it[0].toInt() to it[1].toInt() } }.unzip()
}
