package se.jakub.aoc.day06

import se.jakub.aoc.Day
import se.jakub.aoc.grids.Direction
import se.jakub.aoc.grids.Grid
import se.jakub.aoc.grids.Position

const val GUARD_START_POS = '^'
const val OBSTACLE = '#'

typealias DistinctPath = Pair<Position, Direction>

class Guard(var position: Position, var direction: Direction = Direction.Up) {
  fun rotateRight() {
    direction = direction.rotateRight()
  }

  fun moveTo(position: Position) {
    this.position = position
  }
}

class Day06 : Day {
  override fun part1(lines: List<String>): Any {
    val grid = Grid(lines)
    val guard = Guard(grid.findFirstPositionOf(GUARD_START_POS)!!)
    val distinctPaths = simulateGuard(grid, guard)
    return distinctPaths.map { it.first }.toSet().size
  }

  override fun part2(lines: List<String>): Any {
    val grid = Grid(lines)
    val startPos = grid.findFirstPositionOf(GUARD_START_POS)!!
    return countObstacles(grid, startPos)
  }

  private fun countObstacles(grid: Grid, startPos: Position): Int {
    var obstacles = 0
    for (position in grid.getPositions()) {
      if (position == startPos) continue
      val guard = Guard(startPos)

      val oldValue = grid.get(position)
      grid.set(position, OBSTACLE)

      if (createsCycle(grid, guard)) {
        obstacles++
      }

      grid.set(position, oldValue)
    }
    return obstacles
  }

  private fun createsCycle(grid: Grid, guard: Guard): Boolean {
    val visitedPaths = mutableSetOf<DistinctPath>()
    while (true) {
      if (Pair(guard.position, guard.direction) in visitedPaths) {
        return true
      }

      val (path, foundObstacle) = grid.getPathUntil(guard.position, guard.direction, OBSTACLE)
      visitedPaths.addAll(path.map { Pair(it, guard.direction) })

      if (!foundObstacle) break

      guard.rotateRight()
      guard.moveTo(path.last())
    }
    return false
  }

  private fun simulateGuard(grid: Grid, guard: Guard): MutableSet<DistinctPath> {
    val visitedPaths = mutableSetOf<DistinctPath>()
    while (true) {
      val (path, foundObstacle) = grid.getPathUntil(guard.position, guard.direction, OBSTACLE)
      visitedPaths.addAll(path.map { Pair(it, guard.direction) })

      if (!foundObstacle) break

      guard.rotateRight()
      guard.moveTo(path.last())
    }
    return visitedPaths
  }
}
