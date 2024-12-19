package se.jakub.aoc.day09

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestDay09 {
    @Test
    fun testPart1() {
        val day09 = Day09()
        val input = File("src/main/resources/inputs/day09/day09-part1.txt").readLines()
        val expected = 6370402949053

        assertEquals(expected, day09.part1(input))
    }

    @Test
    fun testPart2() {
        val day09 = Day09()
        val input = File("src/main/resources/inputs/day09/day09-part2.txt").readLines()
        val expected = 6398096697992

        assertEquals(expected, day09.part2(input))
    }
}
