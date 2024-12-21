package se.jakub.aoc.day11

import se.jakub.aoc.Day
import se.jakub.aoc.extensions.getLongs

class Day11 : Day {

  override fun part1(lines: List<String>): Any {
    return solve(lines.first().getLongs(), 25)
  }

  override fun part2(lines: List<String>): Long {
    return solve(lines.first().getLongs(), 75)
  }

  private fun solve(stones: List<Long>, totalSteps: Int): Long {
    val mem = mutableMapOf<Pair<Long, Int>, Long>()

    fun countStones(number: Long, steps: Int): Long {
      if (steps == 0) return 1

      val key = Pair(number, steps)
      if (key in mem) return mem[key]!!

      val count =
          when {
            number == 0L -> countStones(1, steps - 1)
            number.toString().length % 2 == 0 -> {
              val strStone = number.toString()
              val leftStone = strStone.substring(0, strStone.length / 2).toLong()
              val rightStone = strStone.substring(strStone.length / 2).toLong()
              countStones(leftStone, steps - 1) + countStones(rightStone, steps - 1)
            }

            else -> countStones(number * 2024, steps - 1)
          }

      mem[key] = count
      return count
    }

    return stones.sumOf { stone -> countStones(stone, totalSteps) }
  }
}
