package se.jakub.aoc.day02

import java.io.File
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestDay02 {
  @Test
  fun testPart1() {
    val day02 = Day02()
    val input = File("src/main/resources/inputs/day02/day02-part1.txt").readLines()
    val expected = 559

    assertEquals(expected, day02.part1(input))
  }

  @Test
  fun testPart2() {
    val day02 = Day02()
    val input = File("src/main/resources/inputs/day02/day02-part2.txt").readLines()
    val expected = 601

    assertEquals(expected, day02.part2(input))
  }
}
