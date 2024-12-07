package se.jakub.aoc.day04

import se.jakub.aoc.Day
import se.jakub.aoc.grids.Direction
import se.jakub.aoc.grids.Grid

class Day04 : Day {

  override fun part1(lines: List<String>): Any {
    val target = "XMAS"
    val grid = Grid(lines)

    val deltaDirections = Direction.allDirections
    var count = 0

    for ((x, y) in grid.getPositions()) {
      for (direction in deltaDirections) {
        val path = grid.getPath(x, y, steps = 4, direction)
        if (path.joinToString("") == target) {
          count++
        }
      }
    }

    return count
  }

  override fun part2(lines: List<String>): Any {
    val target = "MAS"
    val reverseTarget = target.reversed()
    val grid = Grid(lines)

    var count = 0
    for (x in 2 until grid.cols) {
      for (y in 0 until grid.rows) {
        val path1 = grid.getPath(x - 2, y, steps = 3, Direction.DownRight).joinToString("")
        val path2 = grid.getPath(x, y, steps = 3, Direction.DownLeft).joinToString("")

        if ((path1 == target || path1 == reverseTarget) &&
            (path2 == target || path2 == reverseTarget)) {
          count++
        }
      }
    }

    return count
  }
}
