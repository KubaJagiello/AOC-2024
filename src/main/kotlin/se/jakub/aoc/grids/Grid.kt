package se.jakub.aoc.grids

sealed class Direction(val dx: Int, val dy: Int) {
  data object Up : Direction(0, -1)

  data object Down : Direction(0, 1)

  data object Left : Direction(-1, 0)

  data object Right : Direction(1, 0)

  data object UpLeft : Direction(-1, -1)

  data object UpRight : Direction(1, -1)

  data object DownLeft : Direction(-1, 1)

  data object DownRight : Direction(1, 1)

  companion object {
    val allDirections = listOf(Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight)
  }

  fun rotateRight(): Direction =
      when (this) {
        Up -> Right
        Right -> Down
        Down -> Left
        Left -> Up
        else -> throw RuntimeException("?")
      }

  fun getOppositeDirection(): Direction =
      when (this) {
        Up -> Down
        Right -> Left
        Down -> Up
        Left -> Right
        DownLeft -> UpRight
        DownRight -> UpLeft
        UpLeft -> DownRight
        UpRight -> DownLeft
      }
}

data class Position(val x: Int, val y: Int)

class Grid(lines: List<String>) {
  private val mutableLines: MutableList<StringBuilder> =
      lines.map { StringBuilder(it) }.toMutableList()
  val rows = mutableLines.size
  val cols = if (mutableLines.isNotEmpty()) mutableLines[0].length else 0

  fun get(position: Position): Char = mutableLines[position.y][position.x]

  fun set(position: Position, value: Char) {
    mutableLines[position.y][position.x] = value
  }

  fun findFirstPositionOf(c: Char): Position? {
    for (position in getPositions()) {
      if (get(position) == c) {
        return position
      }
    }
    return null
  }

  fun getPathUntils(
      startPosition: Position,
      direction: Direction,
      until: Char,
  ): List<Position> {
    var newX = startPosition.x
    var newY = startPosition.y
    var i = 0

    val listOfUntils = mutableListOf<Position>()

    while (isInBounds(newX, newY)) {
      newX += direction.dx
      newY += direction.dy

      if (!isInBounds(newX, newY)) {
        return listOfUntils
      }

      if (get(Position(newX, newY)) == until) {
        listOfUntils.add(Position(newX, newY))
      }
      i++
    }

    return listOfUntils
  }

  fun getPathUntil(
      startPosition: Position,
      direction: Direction,
      until: Char,
  ): Pair<List<Position>, Boolean> {
    val path: MutableList<Position> = mutableListOf()
    path.add(startPosition)
    var newX = startPosition.x
    var newY = startPosition.y
    var i = 0

    while (isInBounds(newX, newY)) {
      newX += direction.dx
      newY += direction.dy

      if (!isInBounds(newX, newY)) {
        return Pair(path, false)
      }

      if (get(Position(newX, newY)) == until) {
        return Pair(path, true)
      }

      path.add(Position(newX, newY))
      i++
    }

    return Pair(path, false)
  }

  fun getPath(x: Int, y: Int, steps: Int, direction: Direction): List<Char> {

    val path: MutableList<Char> = mutableListOf()
    for (i in 0 until steps) {
      val newX = i * direction.dx + x
      val newY = i * direction.dy + y

      if (!isInBounds(newX, newY)) return path

      path.add(get(Position(newX, newY)))
    }

    return path
  }

  fun getPositions(): List<Position> = positions

  fun print(grid: Grid, visited: Set<Position> = setOf()) {
    for (y in 0 until grid.rows) {
      for (x in 0 until grid.cols) {
        if (visited.contains(Position(x, y))) {
          print("#")
        } else {
          print(grid.get(Position(x, y)))
        }
      }
      println()
    }
  }

  private val positions: List<Position> =
      (0 until rows).flatMap { x -> (0 until cols).map { y -> Position(x, y) } }

  private fun isInBounds(x: Int, y: Int): Boolean = x in 0 until cols && y in 0 until rows

  fun isInBounds(position: Position): Boolean = isInBounds(position.x, position.y)
}
