package se.jakub.aoc.day10

import java.io.File
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestDay10 {
  @Test
  fun testPart1() {
    val day10 = Day10()
    val input = File("src/main/resources/inputs/day10/day10-part1.txt").readLines()
    val expected = 517

    assertEquals(expected, day10.part1(input))
  }

  @Test
  fun testPart2() {
    val day10 = Day10()
    val input = File("src/main/resources/inputs/day10/day10-part2.txt").readLines()
    val expected = 1116

    assertEquals(expected, day10.part2(input))
  }
}
