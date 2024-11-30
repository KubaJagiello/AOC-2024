package se.jakub.aoc.day01

import java.io.File
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestDay01 {
  @Test
  fun testPart1() {
    val day01 = Day01()
    val input = File("src/main/resources/inputs/day01/day01-part1.txt").readLines()
    val expected = 1603498

    assertEquals(expected, day01.part1(input))
  }

  @Test
  fun testPart2() {
    val day01 = Day01()
    val input = File("src/main/resources/inputs/day01/day01-part2.txt").readLines()
    val expected = 25574739

    assertEquals(expected, day01.part2(input))
  }
}
