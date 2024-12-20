package se.jakub.aoc.day10

import se.jakub.aoc.Day
import se.jakub.aoc.grids.Direction
import se.jakub.aoc.grids.Grid
import se.jakub.aoc.grids.Position

class Day10 : Day {
  override fun part1(lines: List<String>): Any {
    return calculatePaths(lines)
  }

  override fun part2(lines: List<String>): Any {
    return calculatePaths(lines, part2 = true)
  }

  private fun calculatePaths(lines: List<String>, part2: Boolean = false): Int {
    val grid = Grid(lines)

    return startPositions(grid).sumOf { startPosition -> pathsCount(startPosition, grid, part2) }
  }

  private fun pathsCount(startPosition: Position, grid: Grid, part2: Boolean): Int {
    val positionsToVisit = mutableListOf<Position>()
    val visited = mutableSetOf<Position>()
    var totalPaths = 0

    positionsToVisit.add(startPosition)

    while (positionsToVisit.isNotEmpty()) {
      val currentPosition = positionsToVisit.removeFirst()

      if (visited.contains(currentPosition) && !part2) continue

      visited.add(currentPosition)

      val currentHeight = grid.get(currentPosition).digitToInt()

      if (currentHeight == 9) {
        totalPaths++
        continue
      }

      Direction.cardinalDirections
          .map { direction -> currentPosition.move(direction) }
          .filter { newPosition -> grid.isInBounds(newPosition) }
          .filter { newPosition -> grid.get(newPosition).digitToInt() - 1 == currentHeight }
          .forEach { newPosition -> positionsToVisit.addLast(newPosition) }
    }
    return totalPaths
  }

  private fun startPositions(grid: Grid): List<Position> =
      grid.getPositions().filter { grid.get(it) == '0' }
}
