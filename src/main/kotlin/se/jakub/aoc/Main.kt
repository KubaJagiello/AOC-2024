package se.jakub.aoc

import java.io.File
import se.jakub.aoc.day03.Day03

fun main() {
  runDay(Day03())
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
