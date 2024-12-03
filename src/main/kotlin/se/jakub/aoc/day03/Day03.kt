package se.jakub.aoc.day03

import se.jakub.aoc.Day
import se.jakub.aoc.extensions.getInts
import se.jakub.aoc.extensions.product

class Day03 : Day {
  override fun part1(lines: List<String>): Any {
    val mulRegex = """mul\(\d+,\d+\)""".toRegex()

    return lines
        .flatMap { line -> mulRegex.findAll(line) }
        .sumOf { match -> match.value.getInts().product() }
  }

  override fun part2(lines: List<String>): Any {
    val mulRegex = """mul\(\d+,\d+\)|do\(\)|don't\(\)""".toRegex()

    return lines
        .flatMap { line -> mulRegex.findAll(line) }
        .fold(Pair(0, true)) { (acc, enabled), match ->
          when (match.value) {
            "don't()" -> acc to false
            "do()" -> acc to true
            else -> (if (enabled) (acc + match.value.getInts().product()) else acc) to enabled
          }
        }
        .first
  }
}
