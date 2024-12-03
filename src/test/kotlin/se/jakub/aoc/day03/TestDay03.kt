package se.jakub.aoc.day03

import java.io.File
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestDay03 {
  @Test
  fun testPart1() {
    val day03 = Day03()
    val input = File("src/main/resources/inputs/day03/day03-part1.txt").readLines()
    val expected = 175015740

    assertEquals(expected, day03.part1(input))
  }

  @Test
  fun testPart2() {
    val day03 = Day03()
    val input = File("src/main/resources/inputs/day03/day03-part2.txt").readLines()
    val expected = 112272912

    assertEquals(expected, day03.part2(input))
  }
}
