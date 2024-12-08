package se.jakub.aoc.day07

import java.math.BigDecimal
import se.jakub.aoc.Day
import se.jakub.aoc.extensions.getDoubles
import se.jakub.aoc.extensions.permutations

class Day07 : Day {
  override fun part1(lines: List<String>): Any {
    return calculateTotal(lines, listOf("+", "*"))
  }

  override fun part2(lines: List<String>): Any {
    return calculateTotal(lines, listOf("+", "*", "||"))
  }

  private fun calculateTotal(lines: List<String>, operations: List<String>): BigDecimal {
    var total = 0.0
    for (line in lines) {
      val doubles = line.getDoubles()
      val target = doubles.first()
      val numbers = doubles.drop(1)

      if (isValid(target, numbers, operations)) {
        total += target
      }
    }
    return total.toBigDecimal()
  }

  private fun isValid(target: Double, numbers: List<Double>, operations: List<String>): Boolean {
    val operationsSize = numbers.size - 1
    val combinations = operations.permutations(operationsSize)

    for (combination in combinations) {
      val mutableNumbers = numbers.toMutableList()
      var combinationIndex = 0

      while (mutableNumbers.size > 1) {
        val numberA = mutableNumbers.removeFirst()
        val numberB = mutableNumbers.removeFirst()

        val calculatedNumber = calculate(numberA, numberB, combination[combinationIndex])

        if (calculatedNumber > target) break

        mutableNumbers.add(0, calculatedNumber)
        combinationIndex++
      }

      if (mutableNumbers.size == 1 && mutableNumbers.first() == target) return true
    }

    return false
  }

  private fun calculate(a: Double, b: Double, operation: String): Double {
    return when (operation) {
      "+" -> a + b
      "*" -> a * b
      "||" -> concatenateDoubles(a, b)
      else -> throw IllegalArgumentException("Invalid operator")
    }
  }

  private fun concatenateDoubles(a: Double, b: Double): Double {
    val stringA = a.toBigDecimal().toPlainString().split(".")[0]
    val stringB = b.toBigDecimal().toPlainString().split(".")[0]
    return (stringA + stringB).toDouble()
  }
}
