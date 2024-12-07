package se.jakub.aoc.day04

import java.io.File
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestDay04 {
  @Test
  fun testPart1() {
    val day04 = Day04()
    val input = File("src/main/resources/inputs/day04/day04-part1.txt").readLines()
    val expected = 2685

    assertEquals(expected, day04.part1(input))
  }

  @Test
  fun testPart2() {
    val day04 = Day04()
    val input = File("src/main/resources/inputs/day04/day04-part2.txt").readLines()
    val expected = 2048

    assertEquals(expected, day04.part2(input))
  }
}
