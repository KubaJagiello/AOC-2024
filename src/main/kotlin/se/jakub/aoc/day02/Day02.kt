package se.jakub.aoc.day02

import kotlin.math.abs
import se.jakub.aoc.Day
import se.jakub.aoc.extensions.*

typealias Report = List<Int>

class Day02 : Day {

  override fun part1(lines: List<String>): Any {
    return parseReports(lines).count { report -> isSafe(report) }
  }

  override fun part2(lines: List<String>): Any {
    return parseReports(lines).count { report -> isSafeWithOneRemoval(report) }
  }

  private fun parseReports(lines: List<String>): List<Report> {
    return lines.map { line -> line.splitBySpaces().map(String::toInt) }
  }

  private fun isSafe(report: Report): Boolean {
    return report.isSorted() && isWithinRange(report)
  }

  private fun isSafeWithOneRemoval(report: Report): Boolean {
    return report.indices.any { i -> isSafe(report.toMutableList().apply { removeAt(i) }) }
  }

  private fun isWithinRange(report: Report): Boolean =
      report.zipWithNext().all { (a, b) -> abs(a - b) in 1..3 }
}
