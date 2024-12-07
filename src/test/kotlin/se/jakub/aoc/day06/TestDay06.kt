package se.jakub.aoc.day06

import java.io.File
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestDay06 {
  @Test
  fun testPart1() {
    val day06 = Day06()
    val input = File("src/main/resources/inputs/day06/day06-part1.txt").readLines()
    val expected = 5030

    assertEquals(expected, day06.part1(input))
  }

  @Test
  fun testPart2() {
    val day06 = Day06()
    val input = File("src/main/resources/inputs/day06/day06-part2.txt").readLines()
    val expected = 1928

    assertEquals(expected, day06.part2(input))
  }
}
