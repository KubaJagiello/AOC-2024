package se.jakub.aoc

import se.jakub.aoc.day11.Day11
import java.io.File

fun main() {
  runDay(Day11())
}

fun runDay(dayInstance: Day) {
  val dayClassName = dayInstance::class.simpleName!!.lowercase()
  val baseDir = "src/main/resources/inputs/$dayClassName/$dayClassName"

  println("Running $dayClassName")
  println("\nPart 1:")
  println(
      dayInstance.part1(
          File("$baseDir-part1.txt").readLines(),
      ),
  )
  println("\nPart 2:")
  println(
      dayInstance.part2(
          File("$baseDir-part2.txt").readLines(),
      ),
  )
}
